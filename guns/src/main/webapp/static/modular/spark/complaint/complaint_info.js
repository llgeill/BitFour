/**
 * 初始化申诉详情对话框
 */
var ComplaintInfoDlg = {
    complaintInfoData : {}
};

/**
 * 清除数据
 */
ComplaintInfoDlg.clearData = function() {
    this.complaintInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ComplaintInfoDlg.set = function(key, val) {
    this.complaintInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ComplaintInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ComplaintInfoDlg.close = function() {
    parent.layer.close(window.parent.Complaint.layerIndex);
}

/**
 * 收集数据
 */
ComplaintInfoDlg.collectData = function() {
    this
    .set('id')
    .set('title')
    .set('content')
    .set('voucher')
    .set('status')
    .set('complainantsId')
    .set('gmtCreate')
    .set('gmtModified');
}

/**
 * 提交添加
 */
ComplaintInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/spark/complaint/add", function(data){
        Feng.success("添加成功!");
        window.parent.Complaint.table.refresh();
        ComplaintInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.complaintInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ComplaintInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/spark/complaint/update", function(data){
        Feng.success("修改成功!");
        window.parent.Complaint.table.refresh();
        ComplaintInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.complaintInfoData);
    ajax.start();
}

$(function() {

});
