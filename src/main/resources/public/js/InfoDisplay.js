function getCurrentRoomStatus(){
    var content = new XMLHttpRequest();
    content.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            console.log(this.responseText);
            var obj = JSON.parse(this.responseText);
            output = obj
            document.getElementById("content").innerHTML = output;
        }
    }
    content.open("GET", "/RoomStatus", true);
    content.send();
}