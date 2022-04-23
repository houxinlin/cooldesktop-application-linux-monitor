getDisk()

function getDisk() {
    fetch(`${root}/api/dis/get`)
        .then(response => response.json())
        .then(data => {
            for (const item of data) {
                createChart(item)
            }
        });
}
function format(size){
    if (size/1024/1024<1024){
        return parseInt(size/1024/1024)+"MB"
    }
    return parseInt(size/1024/1024/1024)+"GB";
}
function createDes(diskInfo){
    console.log(diskInfo)
    let desc= document.createElement("div")
    desc.innerText=`${diskInfo.name} ${format(diskInfo.free)}/${format(diskInfo.total)}`
    desc.className="disk-desc"
    return desc
}

function createChart(diskInfo) {
    let diskList = document.getElementById("diskList")
    let container= document.createElement("div")
    let chartDiv= document.createElement("div")


    chartDiv.style.width="250px"
    chartDiv.style.height="250px"
    let chart = echarts.init(chartDiv);
    chart.setOption(createData(diskInfo));
    container.appendChild(chartDiv)
    container.appendChild(createDes(diskInfo))

    diskList.appendChild(container)
}
function createData(diskInfo){
    let option = {

        color: ['#dc4646', '#33be5e'],
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left'
        },
        series: [
            {
                name: '',
                type: 'pie',
                data: [
                    { value: diskInfo.use, name: '已使用' },
                    { value: diskInfo.free, name: '空闲' },
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };
    return option
}