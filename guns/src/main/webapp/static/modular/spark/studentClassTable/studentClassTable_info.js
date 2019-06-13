/**
 * 初始化课表详情对话框
 */
var StudentClassTableInfoDlg = {
    studentClassTableInfoData : {}
};

/**
 * 清除数据
 */
StudentClassTableInfoDlg.clearData = function() {
    this.studentClassTableInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StudentClassTableInfoDlg.set = function(key, val) {
    this.studentClassTableInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StudentClassTableInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
StudentClassTableInfoDlg.close = function() {
    parent.layer.close(window.parent.StudentClassTable.layerIndex);
}

/**
 * 收集数据
 */
StudentClassTableInfoDlg.collectData = function() {
    this
    .set('id')
    .set('monday')
    .set('tuesday')
    .set('wednesday')
    .set('thursday')
    .set('friday')
    .set('saturday')
    .set('sunday');
}

/**
 * 提交添加
 */
StudentClassTableInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/spark/studentClassTable/add", function(data){
        Feng.success("添加成功!");
        window.parent.StudentClassTable.table.refresh();
        StudentClassTableInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.studentClassTableInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
StudentClassTableInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/spark/studentClassTable/update", function(data){
        Feng.success("修改成功!");
        window.parent.StudentClassTable.table.refresh();
        StudentClassTableInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.studentClassTableInfoData);
    ajax.start();
}

$(function() {

});
