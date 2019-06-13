/**
 * 初始化兼职审核细节详情对话框
 */
var AuditDetailInfoDlg = {
    auditDetailInfoData : {}
};

/**
 * 清除数据
 */
AuditDetailInfoDlg.clearData = function() {
    this.auditDetailInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AuditDetailInfoDlg.set = function(key, val) {
    this.auditDetailInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AuditDetailInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AuditDetailInfoDlg.close = function() {
    parent.layer.close(window.parent.AuditDetail.layerIndex);
}

/**
 * 收集数据
 */
AuditDetailInfoDlg.collectData = function() {
    this
    .set('id')
    .set('auditorId')
    .set('content')
    .set('sparkAuditId')
    .set('gmtCreate')
    .set('gmtModified');
}

/**
 * 提交添加
 */
AuditDetailInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/spark/auditDetail/add", function(data){
        Feng.success("添加成功!");
        window.parent.AuditDetail.table.refresh();
        AuditDetailInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.auditDetailInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AuditDetailInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/spark/auditDetail/update", function(data){
        Feng.success("修改成功!");
        window.parent.AuditDetail.table.refresh();
        AuditDetailInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.auditDetailInfoData);
    ajax.start();
}

$(function() {

});
