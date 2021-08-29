var roomStatus;

function getCurrentRoomStatus(){
    var content = new XMLHttpRequest();
    content.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            console.log(this.responseText);
            var obj = JSON.parse(this.responseText);
            roomStatus = obj.roomStatus;
            document.getElementById("content").innerHTML = roomStatus;
        }
    }
    content.open("GET", "/RoomStatus", true);
    content.send();
}