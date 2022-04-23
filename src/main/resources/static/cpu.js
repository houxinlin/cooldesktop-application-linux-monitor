let cpuChart = echarts.init(document.getElementById('cpu'));
let cpuData = []

function getCpu(data) {
    if (cpuData.length > 15) {
        cpuData.splice(0, 1)
    }
    let value = data.use * 100
    cpuData.push(value)
    let option = {
        title: {
            text: '内存使用率'
        },
        xAxis: {
            type: 'category',
        },
        yAxis: {
            splitLine: {show: false},
            type: 'value',
            max: 100,
            nameTextStyle: {
                color: '#ffffff'
            },
            axisLabel: {
                color: '#ffffff',
            },
            axisTick: {
                show: false
            },
            axisLine: {
                show: false
            }
        },
        series: [
            {
                data: cpuData,
                type: 'line',
                areaStyle: {},
                smooth: true
            }
        ]
    };
    cpuChart.setOption(option);
}