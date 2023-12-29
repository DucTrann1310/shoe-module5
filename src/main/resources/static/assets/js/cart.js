const pageCart = {
    url: {
        getSizeCartDetail: 'http://localhost:8080/api/shoe-ecommerce/cartDetail',
        increQuantityProductByCardDetailId: 'http://localhost:8080/api/shoe-ecommerce/increQuantity/',
        decreQuantityProductByCardDetailId: 'http://localhost:8080/api/shoe-ecommerce/decreQuantity/',
        removeCartDetail: 'http://localhost:8080/api/shoe-ecommerce/removeCartDetail/',
        checkout: 'http://localhost:8080/api/shoe-ecommerce/checkout'
    },
    commands: {},
    elements: {},
    loadData: {}
}

pageCart.elements.bodyProduct = $(".cart-table tbody")
pageCart.elements.cartUser = $("#cart-user")
pageCart.elements.modalRemoveCartDetail = $("#removeCartDetail")
pageCart.elements.confirmButtonModal = $("#button-confirm-modal")
pageCart.elements.subToal = $(".order-summary .sub-total")
pageCart.elements.totalPriceDetail = $(".order-summary .totalPriceDetail")

pageCart.elements.formCheckout = $("#formCheckout")
pageCart.elements.btnCheckout = $(".btn-checkout")
pageCart.elements.fullNameCheckout = $("#fullNameCheckout")
pageCart.elements.addressCheckout = $("#addressCheckout")
pageCart.elements.emailCheckout = $("#emailCheckout")
pageCart.elements.mobileCheckout = $("#mobileCheckout")

async function getAllCartDetails() {
    return await $.ajax({
        url: pageCart.url.getSizeCartDetail
    })
}

pageCart.loadData.getCartUser = async () => {
    const cartDetails = await getAllCartDetails()
    pageCart.elements.cartUser.empty()
    if (cartDetails.length == 0) {
        const str = `
                    <i class="fa-solid fa-cart-shopping fa-lg" style="padding-right: 10px; margin-top: 13px"></i>
                    <a class="icon-button user-icon" href="/shoe-ecommerce/dashboard/order-list">
                        <i class="fa-solid fa-user fa-lg custom-icon"></i>
                    </a>
                            `
        pageCart.elements.cartUser.append(str)
    }
    if (cartDetails.length > 0) {
        const str = `
                    <a class="icon-button position-relative me-3 cartDetail" href="/shoe-ecommerce/cart">
                        <i class="fa-solid fa-cart-shopping fa-lg custom-icon" style="padding-right: 10px; margin-top: 13px"></i>
                        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">${cartDetails.length}</span>
                    </a>
                    <a class="icon-button user-icon" href="/shoe-ecommerce/dashboard/order-list">
                        <i class="fa-solid fa-user fa-lg custom-icon"></i>
                    </a>
                            `
        pageCart.elements.cartUser.append(str)
    }
}

pageCart.commands.renderCartDetail = async () => {
    pageCart.elements.bodyProduct.empty()
    const cartDetails = await getAllCartDetails();
    const contentCardDetails = cartDetails.map(
        (cartDetail) => `
                    <tr class="tr_${cartDetail.id}">
                        <td style="max-width: 200px;">
                            <div class="d-flex align-items-center">
                                <img src="${cartDetail.product.img}" alt style="margin-right: 4px; padding: 0 5px; width: 40%">
                                <div class="d-inline">
                                    <div class="d-block fw-bolder mb-2 product-name">
                                        ${cartDetail.product.title}
                                    </div>
                                    <div class="badge py-2 " style="background-color:${cartDetail.product.color.name}; 
                                                            color:${cartDetail.product.color.name == "White" ? "Black" : ""};
                                                            border:${cartDetail.product.color.name == "White" ? "1px solid #e0d9d9" : ""};">
                                        ${cartDetail.product.color.name}
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td class="productPrice" style="text-align: right!important;">
                            $${cartDetail.product.newPrice}
                        </td>
                        <td>
                            <div class="cart-quantity-wrap">
                                <div class="cart-quantity">
                                    <span class="minus">-</span>
                                    <span class="quantity">${cartDetail.quantity}</span>
                                    <span class="plus">+</span>
                                </div>
                            </div>
                        </td>
                        <td class="totalPriceProduct" style="text-align: right!important;">
                            $${cartDetail.quantity * cartDetail.product.newPrice}
                        </td>
                        <td>
                            <div class="action-wrap">
                                <button class="btn-remove" data-bs-toggle="modal" data-bs-target="removeCartDetail">
                                    <i class="fa-solid fa-xmark" ></i>
                                </button> 
                            </div>
                        </td>
                    </tr>
                    `
    )

    if(cartDetails.length > 0){
        pageCart.elements.btnCheckout.on("click", () => {
            pageCart.elements.formCheckout.trigger('submit')
        })
    }else {
        pageCart.elements.btnCheckout.on("click", () => {
            AppUtils.showError("There's no product to checkout", "error")
        })
    }

    pageCart.elements.bodyProduct.append(contentCardDetails.join(""))
    pageCart.commands.totalPriceDetail()
    pageCart.commands.increQuantityAndTotal()
    pageCart.commands.decreQuantityAndTotal()
    pageCart.commands.removeCartDetail()
}

pageCart.commands.increQuantityAndTotal = () => {
    const plusButtons = $(".plus");
    plusButtons.on("click", function () {
        const trProduct = $(this).closest("tr");
        pageCart.commands.updateQuantityAndTotal(trProduct, 1, pageCart.url.increQuantityProductByCardDetailId);
    });
}

pageCart.commands.decreQuantityAndTotal = () => {
    const minusButtons = $(".minus");
    minusButtons.on("click", function () {
        const trProduct = $(this).closest("tr");
        pageCart.commands.updateQuantityAndTotal(trProduct, -1, pageCart.url.decreQuantityProductByCardDetailId);
    });
}

pageCart.commands.updateQuantityAndTotal = (trProduct, quantityChange, url) => {
    const quantityContent = trProduct.find(".quantity");
    const totalContent = trProduct.find(".totalPriceProduct");
    const price = parseInt(trProduct.find(".productPrice").text().split("$")[1]);
    const productName = trProduct.find(".product-name").text().trim()
    const currentQuantity = parseInt(quantityContent.text());
    const newQuantity = currentQuantity + quantityChange;

    if (newQuantity >= 1) {
        const totalPrice = `$${price * newQuantity}`;
        quantityContent.html(newQuantity);
        totalContent.html(totalPrice);
        const cartDetailId = trProduct.attr("class").split("tr_")[1];

        $.ajax({
            url: url + cartDetailId,
            method: 'POST'
        })
            .done(() => {
                if (quantityChange > 0) {
                    AppUtils.showSuccess(productName + " has increment quantity", "success")
                }
                if (quantityChange < 0) {
                    AppUtils.showSuccess(productName + " has descrement quantity", "success")
                }
                pageCart.commands.totalPriceDetail()
            });
    }
}

pageCart.commands.removeCartDetail = () => {
    const btnDeletes = $(".btn-remove")
    $.each(btnDeletes, (index, item) => {
        $(item).on("click", () => {
            const trProduct = $(item).closest("tr")
            const cartDetailId = trProduct.attr("class").split("tr_")[1]
            const productName = trProduct.find(".product-name").text().trim()
            Swal.fire({
                title: "Confirm remove cart item?",
                text: "Are you sure to remove this cart item",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#696767",
                confirmButtonText: "Confirm"
            }).then((result) => {
                if (result.isConfirmed) {
                    AppUtils.showSuccess(productName + " has been removed", "info")

                    $.ajax({
                        url: pageCart.url.removeCartDetail + cartDetailId,
                        method: 'POST'
                    })
                        .done(() => {
                            trProduct.remove()
                             pageCart.loadData.getCartUser()
                        })

                }
            });
        })
    })
}

pageCart.commands.totalPriceDetail = () => {
    let totalPriceDetail = 0
    pageCart.elements.subToal.empty()
    pageCart.elements.totalPriceDetail.empty()
    pageCart.elements.totalPriceProduct = $(".totalPriceProduct")
    $.each(pageCart.elements.totalPriceProduct, (index, item) => {
        totalPriceDetail += parseInt($(item).text().split("$")[1].trim());
    });
    const strSubTotal = `
                        $${totalPriceDetail}
                        `
    pageCart.elements.subToal.append(strSubTotal)
    pageCart.elements.totalPriceDetail.append(strSubTotal)
}

pageCart.commands.checkout = () => {
    const fullNameCheckout = pageCart.elements.fullNameCheckout.val()
    const addressCheckout = pageCart.elements.addressCheckout.val()
    const emailCheckout = pageCart.elements.emailCheckout.val()
    const mobileCheckout = pageCart.elements.mobileCheckout.val()

    const customer = {
        fullname: fullNameCheckout,
        address: addressCheckout,
        email: emailCheckout,
        mobile: mobileCheckout
    }

    $.ajax({
        url: pageCart.url.checkout,
        data: JSON.stringify(customer),
        method: 'POST'
    })
        .done(() => {
            pageCart.elements.formCheckout.trigger('reset')
            // pageCart.elements.bodyProduct.empty()
            pageCart.commands.renderCartDetail()
            pageCart.loadData.getCartUser()
            AppUtils.showSuccess("Checkout successfully", "success")
        })
}



pageCart.elements.formCheckout.validate({
    onkeyup: function (element) {
        $(element).valid()
    },
    onclick: false,
    onfocusout: false,
    rules: {
        fullNameCheckout: {
            required: true,
        },
        addressCheckout: {
            required: true,
            minlength: 5
        },
        emailCheckout: {
            required: true,
            email: true
        },
        mobileCheckout: {
            required: true,
            digits: true
        }
    },
    messages: {
        fullNameCheckout: {
            required: "Please enter your fullname",
        },
        addressCheckout: {
            required: "Please enter your address",
            minlength: "At least 5 letters"
        },
        emailCheckout: {
            required: "Please enter your email",
            email: "Invalid email"
        },
        mobileCheckout: {
            required: "Please enter your mobile",
            digit: "Invalid mobile. Mobile number should only contain digits"
        }
    },
    errorPlacement: function (error, element) {
        error.addClass('error-message');
        error.insertAfter(element);
    },
    submitHandler: () =>  {
        pageCart.commands.checkout()
    }
})



$.ajaxSetup({
    headers: {
        "Content-type": "application/json; charset=UTF-8"
    }
})
$(async () => {

    pageCart.loadData.getCartUser()

    await pageCart.commands.renderCartDetail()

})