function isNull(str) {
    return str === null || str === undefined || str.length === 0;
}

function getUrlVariable(variable) {
    var query = window.location.toString();
    var urlParams = query.split("?");
    var vars = urlParams[1].split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] === variable) {
            return pair[1];
        }
    }
    return false;
}

//重新渲染表单
function renderForm() {
    layui.use('form', function () {
        var form = layui.form;//高版本建议把括号去掉，有的低版本，需要加()
        form.render();
    });
}

function getDate() {
    // 获取当前日期
    var date = new Date();
// 获取当前月份
    var nowMonth = date.getMonth() + 1;
// 获取当前是几号
    var strDate = date.getDate();
// 添加分隔符“-”
    var seperator = "-";
// 对月份进行处理，1-9月在前面添加一个“0”
    if (nowMonth >= 1 && nowMonth <= 9) {
        nowMonth = "0" + nowMonth;
    }
// 对月份进行处理，1-9号在前面添加一个“0”
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
// 最后拼接字符串，得到一个格式为(yyyy-MM-dd)的日期
    var nowDate = date.getFullYear() + seperator + nowMonth + seperator + strDate;
    return nowDate;
}

function getLastDate() {
    // 获取当前日期
    var date = new Date();
// 获取当前月份
    var nowMonth = date.getMonth() + 1;
// 获取当前是几号
    var strDate = date.getDate() - 1;
// 添加分隔符“-”
    var seperator = "-";
// 对月份进行处理，1-9月在前面添加一个“0”
    if (nowMonth >= 1 && nowMonth <= 9) {
        nowMonth = "0" + nowMonth;
    }
// 对月份进行处理，1-9号在前面添加一个“0”
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
// 最后拼接字符串，得到一个格式为(yyyy-MM-dd)的日期
    var nowDate = date.getFullYear() + seperator + nowMonth + seperator + strDate;
    return nowDate;
}

function getWorkingShiftNow() {
    var myDate = new Date();
    var myTime = myDate.getHours();
    if (myTime <= 8 || myTime >= 19) {
        return 1;
    } else {
        return 0;
    }
}

function getLastWorkingShift() {
    var d = getWorkingShiftNow();
    if (d === 1) {
        return 0;
    } else {
        return 1;
    }
}

function dateInput(id) {
    var myDate = new Date();
    var myTime = myDate.getHours();
    if (myTime <= 8) {
        set_select_checked("workingShift", 1);
        $("." + id).attr("value", getLastDate());
        $(".workingShift").attr("value", 1);
    } else if (myTime >= 19) {
        set_select_checked("workingShift", 1);
        $("." + id).attr("value", getDate());
        $(".workingShift").attr("value", 1);
    } else {
        set_select_checked("workingShift", 0);
        $("." + id).attr("value", getDate());
        $(".workingShift").attr("value", 0);
    }
    renderForm();
}

function checkToolExist(process) {
    $.ajax({
        url: "/getTool",    //请求的url地址
        dataType: "json",   //返回格式为json
        data: {"process": process},    //参数值
        type: "GET",   //请求方式
        success: function (req) {
            if (req.count === 0) {
                $("#tool_table_div").css("display", "none");
            }
        }
    });
}

function checkEquipmentExist(process) {
    $.ajax({
        url: "/getEquipment",    //请求的url地址
        dataType: "json",   //返回格式为json
        data: {"process": process},    //参数值
        type: "GET",   //请求方式
        success: function (req) {
            if (req.count === 0) {
                $("#equipment_table_div").css("display", "none");
            }
        }
    });
}

function getImportantMatter_long(process) {
    $.ajax({
        url: "/getImportantMatterLong",    //请求的url地址
        dataType: "json",   //返回格式为json
        data: {"process": process},    //参数值
        type: "GET",   //请求方式
        success: function (req) {
            //请求成功时处理
            // console.log(req.data.details);
            $("#importantMatter_long").text(req.data.details);
        }
    });
}

function getImportantMatter_now(process) {
    $.ajax({
        url: "/getImportantMatterNow",    //请求的url地址
        dataType: "json",   //返回格式为json
        data: {"process": process},    //参数值
        type: "GET",   //请求方式
        success: function (req) {
            //请求成功时处理
            // console.log(req.data.details);
            $("#importantMatter_Now").text(req.data.details);
            $("#importantMatter_now_uuid").val(req.data.uuid);
        }
    });
}

/**
 * 设置select控件选中
 * @param selectId select的id值
 * @param checkValue 选中option的值
 * @author 标哥
 */
function set_select_checked(selectId, checkValue) {
    var select = document.getElementById(selectId);

    for (var i = 0; i < select.options.length; i++) {
        if (select.options[i].value == checkValue) {
            select.options[i].selected = true;
            break;
        }
    }
}

/**
 * 工序中英文互转
 * @param process
 * @returns {string}
 */
function processE(process) {
    switch (process) {
        case "目检":
            return "VisualInspection";
        case "厚度":
            return "Thickness";
        case "电阻率":
            return "Resistivity";
        case "颗粒":
            return "Particles";
        case "成品上架":
            return "PutOnShelf";
        case "终检":
            return "FinalInspection";
        case "班长":
            return "Monitor";
    }
}

function processC(process) {
    switch (process) {
        case "VisualInspection":
            return "目检";
        case "Thickness":
            return "厚度";
        case "Resistivity":
            return "电阻率";
        case "Particles":
            return "颗粒";
        case "PutOnShelf":
            return "成品上架";
        case "FinalInspection":
            return "终检";
        case "Monitor":
            return "班长";
    }
}