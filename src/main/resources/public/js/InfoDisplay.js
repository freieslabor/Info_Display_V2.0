var roomStatus;
var content = new XMLHttpRequest();

function getCurrentRoomStatus(){
    content.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var roomStatusObj = JSON.parse(this.responseText);
            roomStatus = roomStatusObj.roomStatus;
            document.getElementById("roomStatus").innerHTML = roomStatus;
        }
    }
    content.open("GET", "/RoomStatus", true);
    content.send();
}

function openRoom(){
    if(roomStatus == "OPEN"){
        alert("The room status is already set to OPEN!");
    }else {
        content.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 201) {
                console.log("RoomStatus changed to open now");
                getCurrentRoomStatus();
            }
        }
        content.open("POST", "/RoomStatus", true);
        content.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        content.send('status=OPEN');
    }
}

function closeRoom(){
    if(roomStatus == "CLOSE"){
        alert("The room status is already set to CLOSE");
    }else {
        content.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 201) {
                console.log("RoomStatus changed to close now");
                getCurrentRoomStatus();
            }
        }
        content.open("POST", "/RoomStatus", true);
        content.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        content.send('status=CLOSE');
    }
}




