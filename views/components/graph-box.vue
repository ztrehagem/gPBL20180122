<template lang="pug">
.graph-box
  #graph-container loading...
</template>

<script>
import Highcharts from 'highcharts';

export default {
  props: {
    graphData: { default: null },
  },
  watch: {
    graphData({ root: { group: groups } }) {
      console.log(groups);
      Highcharts.chart('graph-container', {
        chart: {
          type: 'column',
        },
        title: {
          text: 'Result',
        },
        xAxis: {
          categories: groups.map(group => group.range),
        },
        yAxis: {
          min: 0,
          title: {
            text: 'number of employees'
          },
          stackLabels: {
            enabled: true,
            style: {
              fontWeight: 'bold',
              color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray',
            }
          }
        },
        legend: {
          align: 'right',
          x: -30,
          verticalAlign: 'top',
          y: 25,
          floating: true,
          backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
          borderColor: '#CCC',
          borderWidth: 1,
          shadow: false
        },
        tooltip: {
          headerFormat: 'age: {point.x}<br/>',
          pointFormat: '{series.name}: {point.y}<br/>total employees: {point.stackTotal}'
        },
        plotOptions: {
          column: {
            stacking: 'normal',
            dataLabels: {
              enabled: true,
              color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
            }
          }
        },
        series: [{
          name: 'normal',
          data: groups.map(group => group.sum - group.quit),
        }, {
          name: 'quit',
          data: groups.map(group => parseInt(group.quit)),
        }]
      })
    },
  },
}
</script>

<style lang="stylus">
@require "../variables";

.graph-box {
  padding: 1rem;
  max-width: 600px;

  > #graph-container {
    border: 2px solid $black;
  }
}
</style>
