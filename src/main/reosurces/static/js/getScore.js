function getScore(idTransaction){
    var score = prompt("Enter score", "0");
    if (score==null||score=="") {
        score = "0";
    }else{

    }


    console.log("/transactions/score/"+ idTransaction.toString()+"/"+score.toString());
    window.location.href = "http://localhost:8080/transactions/score/" + idTransaction.toString()+"/"+score.toString();
    return false;
}