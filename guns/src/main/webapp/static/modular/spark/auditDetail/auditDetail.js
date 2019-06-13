/**
 * 兼职审核细节管理初始化
 */
var AuditDetail = {
    id: "AuditDetailTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
AuditDetail.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '审核人员id，也就是sys_user表的id', field: 'auditorId', visible: true, align: 'center', valign: 'middle'},
            {title: '审核回复内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: 'spark_audit表的id，为父id', field: 'sparkAuditId', visible: true, align: 'center', valign: 'middle'},
            {title: '数据创建时间', field: 'gmtCreate', visible: true, align: 'center', valign: 'middle'},
            {title: '数据修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
AuditDetail.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        AuditDetail.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加兼职审核细节
 */
AuditDetail.openAddAuditDetail = function () {
    var index = layer.open({
        type: 2,
        title: '添加兼职审核细节',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/spark/auditDetail/auditDetail_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看兼职审核细节详情
 */
AuditDetail.openAuditDetailDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '兼职审核细节详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/spark/auditDetail/auditDetail_update/' + AuditDetail.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除兼职审核细节
 */
AuditDetail.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/spark/auditDetail/delete", function (data) {
            Feng.success("删除成功!");
            AuditDetail.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("auditDetailId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询兼职审核细节列表
 */
AuditDetail.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    AuditDetail.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = AuditDetail.initColumn();
    var table = new BSTable(AuditDetail.id, "/spark/auditDetail/list", defaultColunms);
    table.setPaginationType("client");
    AuditDetail.table = table.init();
});
