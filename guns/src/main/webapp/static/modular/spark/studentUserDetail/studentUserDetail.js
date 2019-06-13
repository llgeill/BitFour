/**
 * 学生认证管理初始化
 */
var StudentUserDetail = {
    id: "StudentUserDetailTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
StudentUserDetail.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '学号', field: 'studentId', visible: true, align: 'center', valign: 'middle'},
            {title: '密码', field: 'studentPassword', visible: true, align: 'center', valign: 'middle'},
            {title: '真实姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '真实头像', field: 'avatar', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号码', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '电子邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '入学日期', field: 'enrollmentDate', visible: true, align: 'center', valign: 'middle'},
            {title: '学生课表', field: 'studentClassId', visible: true, align: 'center', valign: 'middle'},
            {title: 'sys_user表的用户id', field: 'sysUserId', visible: true, align: 'center', valign: 'middle'},
            {title: '数据创建时间', field: 'gmtCreate', visible: true, align: 'center', valign: 'middle'},
            {title: '数据修改时间', field: 'gmtModified', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
StudentUserDetail.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        StudentUserDetail.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加学生认证
 */
StudentUserDetail.openAddStudentUserDetail = function () {
    var index = layer.open({
        type: 2,
        title: '添加学生认证',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/spark/studentUserDetail/studentUserDetail_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看学生认证详情
 */
StudentUserDetail.openStudentUserDetailDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '学生认证详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/spark/studentUserDetail/studentUserDetail_update/' + StudentUserDetail.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除学生认证
 */
StudentUserDetail.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/spark/studentUserDetail/delete", function (data) {
            Feng.success("删除成功!");
            StudentUserDetail.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("studentUserDetailId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询学生认证列表
 */
StudentUserDetail.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    StudentUserDetail.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = StudentUserDetail.initColumn();
    var table = new BSTable(StudentUserDetail.id, "/spark/studentUserDetail/list", defaultColunms);
    table.setPaginationType("client");
    StudentUserDetail.table = table.init();
});
