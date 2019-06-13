/**
 * 申诉管理初始化
 */
var Complaint = {
    id: "ComplaintTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Complaint.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '申诉标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '申诉内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '申诉凭证（图片），最多9张，保存的地址用逗号隔开', field: 'voucher', visible: true, align: 'center', valign: 'middle'},
            {title: '1 未处理 2 正在处理 3已处理', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '申诉发起者的id', field: 'complainantsId', visible: true, align: 'center', valign: 'middle'},
            {title: '数据创建时间', field: 'gmtCreate', visible: true, align: 'center', valign: 'middle'},
            {title: '数据修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Complaint.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Complaint.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加申诉
 */
Complaint.openAddComplaint = function () {
    var index = layer.open({
        type: 2,
        title: '添加申诉',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/spark/complaint/complaint_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看申诉详情
 */
Complaint.openComplaintDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '申诉详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/spark/complaint/complaint_update/' + Complaint.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除申诉
 */
Complaint.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/spark/complaint/delete", function (data) {
            Feng.success("删除成功!");
            Complaint.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("complaintId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询申诉列表
 */
Complaint.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Complaint.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Complaint.initColumn();
    var table = new BSTable(Complaint.id, "/spark/complaint/list", defaultColunms);
    table.setPaginationType("client");
    Complaint.table = table.init();
});
