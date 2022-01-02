var content = new XMLHttpRequest();

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

function getListOfSeachingItems(){
    var output = "";
    var item = document.getElementById("item").value;

    content.open("GET", "/StuffInSpace/Find", false);
    content.send("?name=" + item);

    content.onreadystatechange = function () {
        var obj = JSON.parse(this.responseText);
        if(obj == ""){
            output = "No Entires in database";
        }else {
            for (var i = 0; i < obj.length; i++){
                output += "<div style='margin-left: 2%;'>" + obj[i].name + "</div><div style='float: right; margin-right: 2%;'><button class='btn btn-primary' onclick='openDetails(" + obj[i] + ")'></button></button></div>"
            }
        }
        document.getElementById("SISItems").innerHTML = output;
    }
}

function openDetails(object){
    $('#ItemDetials').modal('show');
    document.getElementById( "ItemEntryId").setAttribute('value', object.id);
    document.getElementById("ItemEntryName").setAttribute('value', object.name);
    document.getElementById("ItemEntryPosition").setAttribute('value', object.position);
    document.getElementById("ItemEntryInformation").setAttribute('value', object.info);
}