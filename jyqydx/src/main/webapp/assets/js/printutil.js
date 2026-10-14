 var hkey_root,hkey_path,hkey_key;
    hkey_root="HKEY_CURRENT_USER";
    hkey_path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";

//网页打印时清空页眉页脚
function pagesetup_null() {
    try {
        var RegWsh = new ActiveXObject("WScript.Shell")
        hkey_key = "header"
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "")
        hkey_key = "footer"
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "")
    } catch (e) {}
}
function pagesetup_default() {
    try {
        var RegWsh = new ActiveXObject("WScript.Shell")
        hkey_key = "header"
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&w&b页码，&p/&P")
        hkey_key = "footer"
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&u&b&d")
    } catch (e) {
    }
}

function doPrint(printDiv) {
    try{
        pagesetup_null();
        newwin = window.open("", "newwin", "height=" + window.screen.height + ",width=" + window.screen.width + ",toolbar=no,scrollbars=auto,menubar=no");
        newwin.document.body.innerHTML = document.getElementById(printDiv).innerHTML;
        newwin.window.jqprint();
        newwin.window.close();
        pagesetup_default();
    }catch(e){}
}
