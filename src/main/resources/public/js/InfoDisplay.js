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

function getCalanderEntryForCurrentDay(){
    var calender = "";
    content.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var obj = JSON.parse(this.responseText);
            if(obj == ""){
                calender = "No Entries today";
            }else {
                for (var i = 0; i < obj.length; i++) {
                    var jsonString = JSON.stringify(obj[i]);
                    calender += obj[i].date + "<br />" + obj[i].name +
                        "<br /><a><button class='btn btn-success' onclick='openCalenderEntry(" + jsonString + ")'>Open</button></a><br /><br />";
                }
            }
            document.getElementById("responseCalanderInfo").innerHTML = calender;
        }
    }
    content.open("GET", "/calender", true);
    content.send();
}

function openCalenderEntry(jsonString){
    $('#CalenderEntry').modal('show');
    document.getElementById( "id").setAttribute('value', jsonString.id);
    document.getElementById("date").setAttribute('value', jsonString.date);
    document.getElementById("name").setAttribute('value', jsonString.name);
    document.getElementById("comment").setAttribute('value', jsonString.comment);
}

function saveChange(){
    var id = document.getElementById("id").value;
    var date = document.getElementById("date").value;
    var name = document.getElementById("name").value;
    var comment = document.getElementById("comment").value;

    var data = JSON.stringify({"id": id, "date": date, "name": name, "comment": comment});

    content.open("PUT", "/calender", true);
    content.setRequestHeader("Content-Type", "application/json");

    content.onreadystatechange = function () {
        if(this.status == 409){
            alert(this.responseText);
        }
        if (this.readyState == 4 && this.status == 202) {
            alert(this.responseText)
        }
    }
    content.send(data);

    function deleteCheckEntries(){
        
    }

}