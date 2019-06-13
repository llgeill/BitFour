/**
 * 初始化兼职详情数据操作详情对话框
 */
var PartTimeInfoDlg = {
    partTimeInfoData : {}
};

/**
 * 清除数据
 */
PartTimeInfoDlg.clearData = function() {
    this.partTimeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PartTimeInfoDlg.set = function(key, val) {
    this.partTimeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PartTimeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PartTimeInfoDlg.close = function() {
    parent.layer.close(window.parent.PartTime.layerIndex);
}

/**
 * 收集数据
 */
PartTimeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('partTimeTitle')
    .set('partTimeContent')
    .set('partTimeType')
    .set('recruiterNumber')
    .set('workType')
    .set('workStartTime')
    .set('workEndTime')
    .set('workTimeRange')
    .set('genderRequirement')
    .set('workWelfare')
    .set('workPlace')
    .set('settlementCycle')
    .set('publishSchool')
    .set('pubishId')
    .set('publishEmail')
    .set('publishPhone')
    .set('gmtCreate')
    .set('gmtModified');
}

/**
 * 提交添加
 */
PartTimeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/spark/partTime/add", function(data){
        Feng.success("添加成功!");
        window.parent.PartTime.table.refresh();
        PartTimeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.partTimeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PartTimeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/spark/partTime/update", function(data){
        Feng.success("修改成功!");
        window.parent.PartTime.table.refresh();
        PartTimeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.partTimeInfoData);
    ajax.start();
}

$(function() {

});
