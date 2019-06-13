/**
 * 初始化学生认证详情对话框
 */
var StudentUserDetailInfoDlg = {
    studentUserDetailInfoData : {}
};

/**
 * 清除数据
 */
StudentUserDetailInfoDlg.clearData = function() {
    this.studentUserDetailInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StudentUserDetailInfoDlg.set = function(key, val) {
    this.studentUserDetailInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StudentUserDetailInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
StudentUserDetailInfoDlg.close = function() {
    parent.layer.close(window.parent.StudentUserDetail.layerIndex);
}

/**
 * 收集数据
 */
StudentUserDetailInfoDlg.collectData = function() {
    this
    .set('id')
    .set('studentId')
    .set('studentPassword')
    .set('name')
    .set('avatar')
    .set('phone')
    .set('email')
    .set('enrollmentDate')
    .set('studentClassId')
    .set('sysUserId')
    .set('gmtCreate')
    .set('gmtModified');
}

/**
 * 提交添加
 */
StudentUserDetailInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/spark/studentUserDetail/add", function(data){
        Feng.success("添加成功!");
        window.parent.StudentUserDetail.table.refresh();
        StudentUserDetailInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.studentUserDetailInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
StudentUserDetailInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/spark/studentUserDetail/update", function(data){
        Feng.success("修改成功!");
        window.parent.StudentUserDetail.table.refresh();
        StudentUserDetailInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.studentUserDetailInfoData);
    ajax.start();
}

$(function() {

});
