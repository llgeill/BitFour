/**
 * 用户报名管理初始化
 */
var Enroll = {
    id: "EnrollTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Enroll.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '报名状态（1正在报名2报名成功3报名失败）', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '报名者id', field: 'sysUserId', visible: true, align: 'center', valign: 'middle'},
            {title: '兼职信息id', field: 'partTimeId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'gmtCreate', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Enroll.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Enroll.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户报名
 */
Enroll.openAddEnroll = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户报名',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/spark/enroll/enroll_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户报名详情
 */
Enroll.openEnrollDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户报名详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/spark/enroll/enroll_update/' + Enroll.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户报名
 */
Enroll.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/spark/enroll/delete", function (data) {
            Feng.success("删除成功!");
            Enroll.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("enrollId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户报名列表
 */
Enroll.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Enroll.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Enroll.initColumn();
    var table = new BSTable(Enroll.id, "/spark/enroll/list", defaultColunms);
    table.setPaginationType("client");
    Enroll.table = table.init();
});
