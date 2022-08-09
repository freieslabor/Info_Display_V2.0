var spaceInfomationRequest = new XMLHttpRequest();
var domain = "/" + window.location.pathname;

function getAllSpaceInformation(){
    var spaceInfos = "";
    spaceInfomationRequest.open("GET", "/InfoDisplay/SpaceInformation/All", false);
    spaceInfomationRequest.setRequestHeader("Accept", "application/json");
    spaceInfomationRequest.onreadystatechange = function(){
        if(this.readyState === 4 && this.status === 200){
           var spaceInfoJSON = JSON.parse(this.responseText);
           if(spaceInfoJSON === ""){
               spaceInfos = "No Entries in database!";
           }
           for(var i = 0; i < spaceInfoJSON.length; i++){
               var spaceInfoObject = JSON.stringify(spaceInfoJSON[i]);
               spaceInfos += "Title: " + spaceInfoJSON[i].title + " <button type='button' class='btn btn-success' " +
                   "onclick='openSpaceInformation(" + spaceInfoObject + ")'>Open</button>"
           }
           document.getElementById("allSpaceInformations").innerHTML = spaceInfoObject;
        }
    }
    spaceInfomationRequest.send();
}

function openSpaceInformation(siInfo){
    $('#SIDetials').modal('show');
    document.getElementById("spaceInformationID").setAttribute('value',siInfo.uuid);
    document.getElementById("spaceInformationTitle").setAttribute('value', siInfo.title);
    document.getElementById("spaceInformationText").setAttribute('value', siInfo.info);
    document.getElementById("spaceInformationCreationDate").setAttribute('value', siInfo.creationDate);
    document.getElementById("spaceInformationModificationDate").setAttribute('value', siInfo.modificationDate);
}

function createSpaceInformation(){
    var title = document.getElementById("createSpaceInformationTitle").value;
    var info = document.getElementById("createSpaceInformationText").value;

    var siData = JSON.stringify({"title": title, "info": info});

    spaceInfomationRequest.open("POST", "/InfoDisplay/SpaceInformation", false);
    spaceInfomationRequest.setRequestHeader("Content-Type", "application/json");
    spaceInfomationRequest.send(siData);

    spaceInfomationRequest.onreadystatechange = function(){
        if(this.status === 409){
            alert(this.responseText);
        }
        if(this.status === 201){
            alert("Space Information are created!");
            $('CreateSpaceInforamtion').modal('hide');
        }else{
            alert(this.responseText);
        }
    }
}