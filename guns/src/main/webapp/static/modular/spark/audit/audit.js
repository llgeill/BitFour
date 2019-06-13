/**
 * 兼职审核管理初始化
 */
var Audit = {
    id: "AuditTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Audit.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '审核状态(1：待审核 2：再审核  3：已通过 4：未通过）', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '兼职信息id', field: 'partTimeId', visible: true, align: 'center', valign: 'middle'},
            {title: '数据创建时间', field: 'gmtCreate', visible: true, align: 'center', valign: 'middle'},
            {title: '数据修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Audit.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Audit.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加兼职审核
 */
Audit.openAddAudit = function () {
    var index = layer.open({
        type: 2,
        title: '添加兼职审核',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/spark/audit/audit_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看兼职审核详情
 */
Audit.openAuditDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '兼职审核详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/spark/audit/audit_update/' + Audit.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除兼职审核
 */
Audit.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/spark/audit/delete", function (data) {
            Feng.success("删除成功!");
            Audit.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("auditId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询兼职审核列表
 */
Audit.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Audit.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Audit.initColumn();
    var table = new BSTable(Audit.id, "/spark/audit/list", defaultColunms);
    table.setPaginationType("client");
    Audit.table = table.init();
});
