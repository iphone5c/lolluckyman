/**
 * 提交选择的队伍
 * @param key
 */
function submitTeam(key){
    var checked = $('#'+key).val();
    if(isEmpty(checked)){
        $('.bombBoxbg').show()
        return false;
    }
    $(".content-steptwo").show();
    $(".content-stepone").hide();
    document.body.scrollTop=0;
}

function seletedTeam(key,value,old){

    var values=$("input[name="+old+"]");
    for(var i=0;i<values.length;i++){
        values[i].value="";
    }
    $('#'+key).val(value)
}