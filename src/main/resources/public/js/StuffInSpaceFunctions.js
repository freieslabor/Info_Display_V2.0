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