/**
 * 兼职详情数据操作管理初始化
 */
var PartTime = {
    id: "PartTimeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PartTime.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '兼职标题', field: 'partTimeTitle', visible: true, align: 'center', valign: 'middle'},
            {title: '兼职内容', field: 'partTimeContent', visible: true, align: 'center', valign: 'middle'},
            {title: '兼职类型', field: 'partTimeType', visible: true, align: 'center', valign: 'middle'},
            {title: '兼职招聘人数', field: 'recruiterNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '工作种类 0 短招 1长招', field: 'workType', visible: true, align: 'center', valign: 'middle'},
            {title: '工作开始时间', field: 'workStartTime', visible: true, align: 'center', valign: 'middle'},
            {title: '工作结束时间', field: 'workEndTime', visible: true, align: 'center', valign: 'middle'},
            {title: '工作时间范围，一天可能有多个非连续时间段，用‘ ，’分割', field: 'workTimeRange', visible: true, align: 'center', valign: 'middle'},
            {title: '性别要求（1：男 2：女 3: 男+女）', field: 'genderRequirement', visible: true, align: 'center', valign: 'middle'},
            {title: '工作福利，每项福利用逗号分隔', field: 'workWelfare', visible: true, align: 'center', valign: 'middle'},
            {title: '工作地点', field: 'workPlace', visible: true, align: 'center', valign: 'middle'},
            {title: '结算周期 1.日结 2.周结 3.月结 4.完工结', field: 'settlementCycle', visible: true, align: 'center', valign: 'middle'},
            {title: '发布的学校对象', field: 'publishSchool', visible: true, align: 'center', valign: 'middle'},
            {title: '发布者id', field: 'pubishId', visible: true, align: 'center', valign: 'middle'},
            {title: '发布兼职者的个人邮箱', field: 'publishEmail', visible: true, align: 'center', valign: 'middle'},
            {title: '发布兼职者的电话号码', field: 'publishPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '数据创建时间', field: 'gmtCreate', visible: true, align: 'center', valign: 'middle'},
            {title: '数据修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PartTime.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PartTime.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加兼职详情数据操作
 */
PartTime.openAddPartTime = function () {
    var index = layer.open({
        type: 2,
        title: '添加兼职详情数据操作',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/spark/partTime/partTime_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看兼职详情数据操作详情
 */
PartTime.openPartTimeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '兼职详情数据操作详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/spark/partTime/partTime_update/' + PartTime.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除兼职详情数据操作
 */
PartTime.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/spark/partTime/delete", function (data) {
            Feng.success("删除成功!");
            PartTime.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("partTimeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询兼职详情数据操作列表
 */
PartTime.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PartTime.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PartTime.initColumn();
    var table = new BSTable(PartTime.id, "/spark/partTime/list", defaultColunms);
    table.setPaginationType("client");
    PartTime.table = table.init();
});
