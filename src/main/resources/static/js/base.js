//https://sweetalert2.github.io/#declarative-templates

const messge = (title = "提示", icon = 'success') => {
    Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
    }).fire({
        icon: icon,
        title: title
    });
}
//
// $.fn.serializeObject = () => {
//     const o = {};
//     const a = this.serializeArray();
//     $.each(a, function () {
//         if (o[this.name] !== undefined) {
//             if (!o[this.name].push) {
//                 o[this.name] = [o[this.name]];
//             }
//             o[this.name].push(this.value || '');
//         } else {
//             o[this.name] = this.value || '';
//         }
//     });
//     return o;
// };

const scallPu = () => {
    // alert(11)
    const el = document.documentElement;
    const element = document.documentElement;
    if (!$('body').hasClass("full-screen")) {
        $('body').addClass("full-screen");
        // glyphicon glyphicon-resize-full  出
        // glyphicon glyphicon-resize-small 入
        $('#alarm-fullscreen-toggler').removeClass("glyphicon glyphicon-resize-full");
        $('#alarm-fullscreen-toggler').addClass("glyphicon glyphicon-resize-small");
        if (element.requestFullscreen) {
            element.requestFullscreen();
        } else if (element.mozRequestFullScreen) {
            element.mozRequestFullScreen();
        } else if (element.webkitRequestFullscreen) {
            element.webkitRequestFullscreen();
        } else if (element.msRequestFullscreen) {
            element.msRequestFullscreen();
        }
    } else {
        $('body').removeClass("full-screen");
        $('#alarm-fullscreen-toggler').removeClass("glyphicon glyphicon-resize-small");
        $('#alarm-fullscreen-toggler').addClass("glyphicon glyphicon-resize-full");
        if (document.exitFullscreen) {
            document.exitFullscreen();
        } else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        } else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
        } else if (document.msExitFullscreen) {
            document.msExitFullscreen();
        }
    }
}

/**
 * 字典翻译
 * @param dictStr
 * @param key
 * @returns {string}
 */
const getDict = (dictStr, key) => {
    const strArr = dictStr.split("=");
    const keyVal = strArr[key];
    const indexEnd = keyVal.indexOf(",") != -1 ? keyVal.indexOf(",") :
        keyVal.indexOf("}");
    return keyVal.substr(0, indexEnd)
}

// function loginUrl() {
//     var url = location.href;
//     $.ajax({
//         url: "/login",
//         data: {username, password, rememberMe},
//         type: "GET",
//     })
//     $.get("/login"+ url,
//         function (data, status) {
//               alert("数据: " + data + "\n状态: " + status)
//         }
//     );
// }

const getUrl = (arr = []) => {
    if (arr == null) return "";
    const arrJson = JSON.stringify(arr);
    console.log(arrJson);
}


const login = () => {
    const username = $("#username").val();
    const password = $("#password").val();
    // var vcode = $("#vcode").val();
    const rememberMe = $('#rememberMe').is(':checked');
    if (username == "" || password == "") return;
    $.ajax({
        url: "/ajaxLogin",
        data: {username, password, rememberMe},
        type: "post",
        dataType: "json",
        success: (data) => {
            if (data.status == 200) {
                messge("登陆成功");
                setTimeout(() => {
                    location.href = data.other;
                }, 500);
            } else {
                messge(data.message, "error");
            }
        },
        error: (data) => {
            console.log(data)
            messge("登陆失败，请检查输入", "error");
        }
    });
}