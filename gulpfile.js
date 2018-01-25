const path = require('path');
const fs = require('fs');
const gulp = require('gulp');
const log = require('fancy-log');
const pug = require('gulp-pug');
const stylus = require('gulp-stylus');
const postcss = require('gulp-postcss');
const autoprefixer = require('autoprefixer');
const csswring = require('csswring');
const nop = require('gulp-nop');
const uglify = require('gulp-uglify');
const sourcemaps = require('gulp-sourcemaps');
const md5 = require('gulp-md5-assets');
const plumber = require('gulp-plumber');
const watchify = require('watchify');
const browserify = require('browserify');
const babelify = require('babelify');
const buffer = require('vinyl-buffer');
const source = require('vinyl-source-stream');
const del = require('del');

const srcPath = (...paths) => path.resolve(__dirname, 'views', ...paths);
const destPath = (...paths) => path.resolve(__dirname, 'webapp', 'ROOT', ...paths);
const ENTRY = {
  PUG: srcPath('index.pug'),
  STYLUS: srcPath('index.styl'),
  JS: srcPath('index.js'),
};
const WATCH = {
  PUG: srcPath('**/*.pug'),
  STYLUS: srcPath('**/*.styl'),
  JS: srcPath('**/*.js'),
};
const DEST = {
  HTML: destPath(''),
  CSS: destPath(''),
  JS: destPath(''),
};
const ASSETS = {
  HTML: destPath('**/*.html'),
  CSS: destPath('**/*.css'),
  JS: destPath('**/*.js'),
};
const flags = {
  production: false,
  watchingJs: false,
};

gulp.task('clean', () => del.sync([destPath('**/*.@(html|css|js|map)')]));

gulp.task('enable-production', () => flags.production = true);

gulp.task('enable-wathing-js', () => flags.watchingJs = true);

gulp.task('pug', () => gulp.src(ENTRY.PUG)
  .pipe(plumber())
  .pipe(pug())
  .pipe(gulp.dest(DEST.HTML))
);

gulp.task('stylus', () => gulp.src(ENTRY.STYLUS)
  .pipe(plumber())
  .pipe(flags.production ? nop() : sourcemaps.init())
  .pipe(stylus())
  .pipe(postcss([autoprefixer(), csswring()]))
  .pipe(flags.production ? nop() : sourcemaps.write('./'))
  .pipe(gulp.dest(DEST.CSS))
);

gulp.task('js', () => {
  const destFileName = path.basename(ENTRY.JS);

  const bundler = browserify({
    entries: ENTRY.JS,
    debug: flags.production ? false : true,
    plugin: flags.watchingJs ? watchify : null,
  });

  const bundle = () => {
    log('building...');
    return bundler.bundle()
      .on('error', (error) => {
        log('js bundle error');
        log(error.toString());
      })
      .pipe(source(destFileName))
      .pipe(plumber())
      .pipe(buffer())
      .pipe(flags.production ? nop() : sourcemaps.init({loadMaps: true}))
      .pipe(uglify())
      .pipe(flags.production ? nop() : sourcemaps.write('./'))
      .pipe(gulp.dest(DEST.JS))
      .on('end', () => log(`wrote to '${destFileName}'`));
  }

  bundler.on('update', bundle);

  return bundle();
});

gulp.task('default', ['clean', 'pug', 'stylus', 'js'], () => gulp.src([ASSETS.CSS, ASSETS.JS])
  .pipe(md5(null, ASSETS.HTML))
);

gulp.task('production', ['enable-production', 'default']);

gulp.task('watch', ['enable-wathing-js', 'default'], () => {
  gulp.watch(WATCH.PUG, ['pug']);
  gulp.watch(WATCH.STYLUS, ['stylus']);
});
