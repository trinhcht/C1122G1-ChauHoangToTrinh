function ChangeMoney() {
    let amount = document.getElementById("amount").value;
    let fromc = document.getElementById("from").value;
    let to = document.getElementById("to").value;
    let result='';
    if (fromc == "USD" && to == "VND") {
        result = "result: " + (amount * 23000) + " Đ";
    }
    else if (fromc == "VND"&& To =="USD"){
        result = "result: "+ (amount / 23000) + " $";
    }
    else if (fromc == "VND") {
        result = "result: " + amount + " Đ";
    } else {
        result = "result: " + amount + " $";
    }
    document.getElementById("result").innerHTML = result;
}