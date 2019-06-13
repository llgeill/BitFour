/**
 * 课表管理初始化
 */
var StudentClassTable = {
    id: "StudentClassTableTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
StudentClassTable.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '星期一', field: 'monday', visible: true, align: 'center', valign: 'middle'},
            {title: '星期二', field: 'tuesday', visible: true, align: 'center', valign: 'middle'},
            {title: '星期三', field: 'wednesday', visible: true, align: 'center', valign: 'middle'},
            {title: '星期四', field: 'thursday', visible: true, align: 'center', valign: 'middle'},
            {title: '星期五', field: 'friday', visible: true, align: 'center', valign: 'middle'},
            {title: '星期六', field: 'saturday', visible: true, align: 'center', valign: 'middle'},
            {title: '星期日', field: 'sunday', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
StudentClassTable.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        StudentClassTable.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加课表
 */
StudentClassTable.openAddStudentClassTable = function () {
    var index = layer.open({
        type: 2,
        title: '添加课表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/spark/studentClassTable/studentClassTable_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看课表详情
 */
StudentClassTable.openStudentClassTableDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '课表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/spark/studentClassTable/studentClassTable_update/' + StudentClassTable.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除课表
 */
StudentClassTable.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/spark/studentClassTable/delete", function (data) {
            Feng.success("删除成功!");
            StudentClassTable.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("studentClassTableId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询课表列表
 */
StudentClassTable.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    StudentClassTable.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = StudentClassTable.initColumn();
    var table = new BSTable(StudentClassTable.id, "/spark/studentClassTable/list", defaultColunms);
    table.setPaginationType("client");
    StudentClassTable.table = table.init();
});
