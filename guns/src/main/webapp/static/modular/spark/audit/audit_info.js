/**
 * 初始化兼职审核详情对话框
 */
var AuditInfoDlg = {
    auditInfoData : {}
};

/**
 * 清除数据
 */
AuditInfoDlg.clearData = function() {
    this.auditInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AuditInfoDlg.set = function(key, val) {
    this.auditInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AuditInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AuditInfoDlg.close = function() {
    parent.layer.close(window.parent.Audit.layerIndex);
}

/**
 * 收集数据
 */
AuditInfoDlg.collectData = function() {
    this
    .set('id')
    .set('status')
    .set('partTimeId')
    .set('gmtCreate')
    .set('gmtModified');
}

/**
 * 提交添加
 */
AuditInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/spark/audit/add", function(data){
        Feng.success("添加成功!");
        window.parent.Audit.table.refresh();
        AuditInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.auditInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AuditInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/spark/audit/update", function(data){
        Feng.success("修改成功!");
        window.parent.Audit.table.refresh();
        AuditInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.auditInfoData);
    ajax.start();
}

$(function() {

});
