


//获取url地址携带的参数
export function getUrlParams(param) {
    var reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

//获取url地址携带的参数
export function getParam(oname, url11) {
    url11 = decodeURIComponent(url11); //进行解码
    if (url11.indexOf("?") != -1) {
        var str11 = url11.substr(1); //获取?号后面部分的值
        var strs11 = str11.split("&"); //依照&分割成字符串数组
        for (var i = 0; i < strs11.length; i++) {
            if (strs11[i].split("=")[0] == oname) //循环遍历找到=号左边的与oname相同的参数
            {
                return strs11[i].split("=")[1]; //获取参数值
            }
        }
    }
    return "";
}

//时间格式转换parseTime(onTime, '{h}:{i}:{s}')
export function parseTime(time, cFormat) {
    if (arguments.length === 0) {
        return null
    }
    const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
    let date
    if (typeof time === 'object') {
        date = time
    } else {
        if (('' + time).length === 10) time = parseInt(time) * 1000
        date = new Date(time)
    }
    const formatObj = {
        y: date.getFullYear(),
        m: date.getMonth() + 1,
        d: date.getDate(),
        h: date.getHours(),
        i: date.getMinutes(),
        s: date.getSeconds(),
        a: date.getDay()
    }
    const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
        let value = formatObj[key]
        if (key === 'a') return ['一', '二', '三', '四', '五', '六', '日'][value - 1]
        if (result.length > 0 && value < 10) {
            value = '0' + value
        }
        return value || 0
    })
    return time_str
}

export function browserRedirect() {

  let sUserAgent = navigator.userAgent.toLowerCase();
  return sUserAgent.match(/rv:1.2.3.4/i)

  // let bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
  // let bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
  // let bIsMidp = sUserAgent.match(/midp/i) == "midp";
  // let bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
  // let bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
  // let bIsAndroid = sUserAgent.match(/android/i) == "android";
  // let bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
  // let bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
  // document.writeln("您的浏览设备为：");
  // if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
  //   location.href="https://www.baidu.com";
  // }
  // else {
  //   document.writeln("pc");
  // }
}

//获取移动设备的系统和系统版本
export function mobileDetect(){
  var device_type = navigator.userAgent;//获取userAgent信息
  var md = new MobileDetect(device_type);//实例化mobile-detect
  var os = md.os();//获取系统
  var version = "";
  var device = {}
  if (os == "iOS") {//ios系统的处理
    os = md.os();
    version = md.version("iPhone");
  } else if (os == "AndroidOS") {//Android系统的处理
    os = md.os().replace(/AndroidOS/, "Android");
    version = md.version("Android");
  }
  device.type = os   //系统
  device.version = version  //系统版本
  return device
}









