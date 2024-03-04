//退出登录
function logout() {

    $.confirm({
        title: '退出提示',
        content: '确定要退出吗？',
        buttons: {
            confirm: {
                text: "确定",
                action: function () {
                    window.location.href = "logout?username="+currentUserInfo.userName
                }
            },
            cancel: {
                text: "取消",
                action: function () {
                    return
                }
            },

        }
    });

}

function commonMsg(msg) {

    $.confirm({
        title: '提示',
        content: msg,
        buttons: {
            cancel: {
                text: "确定",
                action: function () {
                }
            },

        }
    });

}


function commonMsgAutoClose(message) {
    $.confirm({
        title: '提示',
        content: message,
        autoClose: 'cancelAction|5000',
        buttons: {
            cancelAction: {
                text: "关闭",
                action: function () {

                }
            }
        }
    });

}
function noTokenToast(message) {
    $.confirm({
        title: '提示',
        content: message,
        autoClose: 'cancelAction|5000',
        buttons: {
            test:{
                text:"跳转首页",
                action: function(){
                    window.location.href = "logout?username="+currentUserInfo.userName
                }
            },
            cancelAction: {
                text: "关闭",
                action: function () {

                }
            }
        }
    });

}

function wensocketErrorToast() {
    $.confirm({
        title: '提示',
        content: "连接异常，请重新登录，即将跳转首页",
        autoClose: 'cancelAction|5000',
        buttons: {
            test:{
                text:"跳转首页",
                action: function(){
                    window.location.href = "logout?username="+currentUserInfo.userName
                }
            },
            cancelAction: {
                text: "取消跳转",
                action: function () {

                }
            }
        }
    });

}

function videoNoUserToast() {
    $.confirm({
        title: '提示',
        content: "请先点击好友列表选择视频对象",
        autoClose: 'cancelAction|5000',
        buttons: {
            cancelAction: {
                text: "确认",
                action: function () {

                }
            }
        }
    });

}