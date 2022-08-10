var sisRequest = new XMLHttpRequest();
var searchedItem;

function createStuffInSpaceItem(){
    var name = document.getElementById("ItemName").value;
    var position = document.getElementById("ItemPostion").value;
    var info = document.getElementById("ItemInfomation").value;

    var data = JSON.stringify({"name": name, "position": position, "info": info});

    sisRequest.open("POST", "/InfoDisplay/StuffInSpace", false);
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

    sisRequest.open("GET", "/InfoDisplay/StuffInSpace/Find?name=" + item, false);
    sisRequest.send();

    sisRequest.onreadystatechange = function () {
        if(this.status == 200) {
            var obj = JSON.parse(this.responseText);
            if (obj == "") {
                searchedItem = "No Entires in database";
            } else {
                for (var i = 0; i < obj.length; i++) {
                    var detailJSON = JSON.stringify(obj[i]);
                    searchedItem += "<li class='list-group-item'><div style='margin-left: 2%;'>" + obj[i].name + "</div><div style='float: right; margin-right: 2%; margin-top: -30px'><button class='btn btn-primary' onclick='openDetails(" + detailJSON + ")'>Open</button></div></li>"
                }
            }
            $('#searchItems').modal('show');
            $("#searchItemsBody").html(searchedItem);
        }else{
            alert(this.status + '<br />' + this.responseText);
        }
    }
}

function searchingItemFromModal(){
    searchedItem = "";
    var item = document.getElementById("itemForSearching").value;

    sisRequest.open("GET", "/InfoDisplay/StuffInSpace/Find?name=" + item, false);
    sisRequest.send();

    sisRequest.onreadystatechange = function () {
        if(this.status === 200) {
            var obj = JSON.parse(this.responseText);
            if (obj == "") {
                searchedItem = "No Entires in database";
            } else {
                for (var i = 0; i < obj.length; i++) {
                    var detailJSON = JSON.stringify(obj[i]);
                    searchedItem += "<li class='list-group-item'><div style='margin-left: 2%;'>" + obj[i].name + "</div><div style='float: right; margin-right: 2%; margin-top: -30px'><button class='btn btn-primary' onclick='openDetails(" + detailJSON + ")'>Open</button></div></li>"
                }
            }
            $("#returnSearchedItems").html(searchedItem);
        }else{
            alert(this.status + '<br />'+ this.responseText);
        }
    }
}

function openDetails(object){
    $('#ItemDetials').modal('show');
    document.getElementById( "ItemEntryId").setAttribute('value', object.uuid);
    document.getElementById("ItemEntryName").setAttribute('value', object.name);
    document.getElementById("ItemEntryPosition").setAttribute('value', object.position);
    document.getElementById("ItemEntryInformation").setAttribute('value', object.info);
}

function deleteEntry(){
    var id = document.getElementById("ItemEntryId").value;
    sisRequest.open("DELETE", "/InfoDisplay/StuffInSpace?id=" + id, false);
    sisRequest.send();

    sisRequest.onreadystatechange = function () {
        if(this.status === 409){
            alert(this.responseText);
        }
        if (this.status === 202){
            alert("Deleting was successful!");
        }else{
            alert("Unknown Failure! <br />" + this.responseText);
        }
    }
}