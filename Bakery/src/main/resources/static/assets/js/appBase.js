class AppBase {
    static DOMAIN = location.origin;
    static BASE_URL_PRODUCT = this.DOMAIN + "/api/products";
    static API_CATEGORY = this.DOMAIN + '/api/products/category';
    static API_DELETE = this.DOMAIN + '/api/products';
    static API_UPDATE = this.DOMAIN + '/api/products';
    static API_DO_UPDATE = this.DOMAIN + '/api/products';

    static showSuccessAlert(t) {
        Swal.fire({
            icon: 'success',
            title: t,
            position: 'center',
            showConfirmButton: false,
            timer : 15000
        })
    }

    static showErrorAlert(t) {
        Swal.fire({
            icon: 'error',
            title: 'Warning',
            text: t,
        })
    }
    static AlertMessageEn = class {
        static SUCCESS_CREATED = "Successful data generation !";
        static SUCCESS_UPDATED = "Data update successful !";
        static SUCCESS_DEPOSIT = "Deposit transaction successful !";
        static SUCCESS_WITHDRAW = "Withdrawal transaction successful !";
        static SUCCESS_TRANSFER = "Transfer transaction successful !";
        static SUCCESS_DEACTIVATE = "Deactivate the customer successfully !";

        static ERROR_400 = "The operation failed, please check the data again.";
        static ERROR_401 = "Unauthorized - Your access token has expired or is not valid.";
        static ERROR_403 = "Forbidden - You are not authorized to access this resource.";
        static ERROR_404 = "Not Found - The resource has been removed or does not exist";
        static ERROR_500 = "Internal Server Error - The server system is having problems or cannot be accessed.";

        static ERROR_CREATED = 'Adding new customer failed';

        static ERROR_LOADING_PROVINCE = "Loading list of provinces - cities failed !";
        static ERROR_LOADING_DISTRICT = "Loading list of district - ward failed !"
        static ERROR_LOADING_WARD = "Loading list of wards - communes - towns failed !";

        static ERROR_LOADING_PRODUCT = "Loading list of products failed !"
    }
}