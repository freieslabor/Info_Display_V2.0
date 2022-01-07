var sisRequest = new XMLHttpRequest();
var searchedItem;

function createStuffInSpaceItem(){
    var name = document.getElementById("ItemName").value;
    var position = document.getElementById("ItemPostion").value;
    var info = document.getElementById("ItemInfomation").value;

    var data = JSON.stringify({"name": name, "position": position, "info": info});

    sisRequest.open("POST", "/StuffInSpace", false);
    sisRequest.setRequestHeader("Content-Type", "application/json");
    sisRequest.send(data);

    sisRequest.onreadystatechange = function(){
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

    sisRequest.open("GET", "/StuffInSpace/Find?name=" + item, false);
    sisRequest.send();

    sisRequest.onreadystatechange = function () {
        var obj = JSON.parse(this.responseText);
        console.log(obj);
        if(obj == ""){
            searchedItem = "No Entires in database";
        }else {
            for (var i = 0; i < obj.length; i++){
                searchedItem += "<div style='margin-left: 2%;'>" + obj[i].name + "</div><div style='float: right; margin-right: 2%;'><button class='btn btn-primary' onclick='openDetails(" + obj[i] + ")'>Open</button></div>"
            }
        }
        $('#searchItems').modal('show');
        $("#searchItemsBody").html(searchedItem);
    }
}

function openDetails(object){
    $('#ItemDetials').modal('show');
    document.getElementById( "ItemEntryId").setAttribute('value', object.id);
    document.getElementById("ItemEntryName").setAttribute('value', object.name);
    document.getElementById("ItemEntryPosition").setAttribute('value', object.position);
    document.getElementById("ItemEntryInformation").setAttribute('value', object.info);
}