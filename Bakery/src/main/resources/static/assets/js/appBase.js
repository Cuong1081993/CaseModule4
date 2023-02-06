class AppBase {
    static DOMAIN = location.origin;
    static BASE_URL_PRODUCT = this.DOMAIN + "/api/products";
    static API_CATEGORY = this.DOMAIN + '/api/products/category';

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
}