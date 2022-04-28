let memoryChart = echarts.init(document.getElementById('memory'));
let memoryData = []
function getMemory(data){
    if (memoryData.length > 15) {
        memoryData.splice(0, 1)
    }
    let value = data.use / data.total * 100
    memoryData.push(value)
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
            axisLine: { //y轴
                show: false
            }
        },
        series: [
            {
                data: memoryData,
                type: 'line',
                areaStyle: {},
                smooth: true
            }
        ]
    };
    memoryChart.setOption(option);
    memoryChart.setOption(option);
}