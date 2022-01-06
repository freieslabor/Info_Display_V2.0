var content = new XMLHttpRequest();
var searchedItem;

function createStuffInSpaceItem(){
    var name = document.getElementById("ItemName").value;
    var position = document.getElementById("ItemPostion").value;
    var info = document.getElementById("ItemInfomation").value;

    var data = JSON.stringify({"name": name, "position": position, "info": info});

    content.open("POST", "/StuffInSpace", false);
    content.setRequestHeader("Content-Type", "application/json");
    content.send(data);

    content.onreadystatechange = function(){
        if(this.status == 201){
            alert(this.responseText);
        }else{
            console.log("Request failed! " + this.responseText);
            alert(this.responseText);
        }
    }
}

function seachingItems(){
    searchedItem = "";
    var item = document.getElementById("itemForSearch").value;

    console.log(item);
    content.open("GET", "/StuffInSpace/Find?name=" + item, false);
    content.send();

    content.onreadystatechange = function () {
        console.log(this.responseText);
        var obj = JSON.parse(this.responseText);
        if(obj == ""){
            searchedItem = "No Entires in database";
        }else {
            for (var i = 0; i < obj.length; i++){
                searchedItem += "<div style='margin-left: 2%;'>" + obj[i].name + "</div><div style='float: right; margin-right: 2%;'><button class='btn btn-primary' onclick='openDetails(" + obj[i] + ")'></button></div>"
            }
        }
    }
}


function openDetails(object){
    $('#ItemDetials').modal('show');
    document.getElementById( "ItemEntryId").setAttribute('value', object.id);
    document.getElementById("ItemEntryName").setAttribute('value', object.name);
    document.getElementById("ItemEntryPosition").setAttribute('value', object.position);
    document.getElementById("ItemEntryInformation").setAttribute('value', object.info);
}