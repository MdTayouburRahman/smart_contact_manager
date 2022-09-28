console.log("Hello i am working");

const toggleSidebar = () => {

    if($(".sidebar").is(":visible")){
        //close side bar 
        $(".sidebar").css("display", "none");
        $(".mycontent").css("margin-left", "0%");
    }else{
        $(".sidebar").css("display", "block");
        $(".mycontent").css("margin-left", "20%");
    }
};