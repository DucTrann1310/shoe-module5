const pageOrderList = {
    url:{
        getAllOrders: 'http://localhost:8080/api/shoe-ecommerce/order-list/orders',
        getOrderByOrderId: 'http://localhost:8080/api/shoe-ecommerce/order-list/order/',
        getOrderDetailByOrderId: 'http://localhost:8080/api/shoe-ecommerce/order-list/orderDetail/'
    },
    elements: {},
    commands: {},
    loadData: {}
}

pageOrderList.elements.orderListContent = $(".table-list tbody")

async function getAllOrders(){
    return await $.ajax({
        url: pageOrderList.url.getAllOrders
    })
}

pageOrderList.loadData.getAllOrders = async () => {
    const orders = await getAllOrders();
    const orderContent = orders.map(
        (order) => `
                        <tr>
                            <td class="text-end">${order.orderDate}</td>
                            <td class="text-end">${order.totalProducts}</td>
                            <td class="text-end">$${order.subtotal}</td>
                            <td class="text-end">Free</td>
                            <td class="text-end">$${order.subtotal}</td>
                            <td class="text-end">
                            <span class="badge bg-secondary">draft</span>
                            </td>
                            <td class="text-end">${order.customer.fullname}</td>
                            <td class="text-end">
                                <button class="icon-button order-detail-btn" data-id="${order.id}" style="padding-right: 10px">
                                <i class="fa-solid fa-indent text-success"></i>
                                </button>
                            </td>
                        </tr>
                        `
    )
    pageOrderList.elements.orderListContent.append(orderContent.join(""))
    pageOrderList.commands.orderDetail()
}

pageOrderList.commands.orderDetail = () => {
    pageOrderList.elements.btnOrderDetails = $(".order-detail-btn")
    pageOrderList.elements.orderDetailContent = $(".order-detail-content")
    pageOrderList.elements.btnClose = $(".btn-close")
    pageOrderList.elements.orderInfo = $(".order-info")
    pageOrderList.elements.customerInfo = $(".customer-info")
    pageOrderList.elements.orderDetailInfo = $(".order-detail-info table tbody")
    $.each(pageOrderList.elements.btnOrderDetails, (index,item) => {
        $(item).on("click", () => {
            const trOrder = $(item).closest("div")
            console.log(trOrder)
            const orderId = $(item).data("id");
            trOrder.removeClass("col-md-12")
            trOrder.addClass("col-md-7")
            pageOrderList.elements.orderDetailContent.removeClass("hide")
            pageOrderList.elements.orderInfo.empty()
            pageOrderList.elements.customerInfo.empty()
            pageOrderList.elements.orderDetailInfo.empty()


            $.ajax({
                url: pageOrderList.url.getOrderByOrderId + orderId
            })
                .done((data) => {
                    const strOrderInfo =`
                                               <h6>Order Infomation</h6>
                                               <div class="d-flex justify-content-between mb-2">
                                                    <span>Subtotal</span>
                                                    <span class="fw-bolder">$${data.subtotal}</span>
                                                </div>
                                                <div class="d-flex justify-content-between mb-2">
                                                    <span>Shipping</span>
                                                    <span class="fw-bolder">Free</span>
                                                </div>
                                                <div class="d-flex justify-content-between mb-2">
                                                    <span>Total</span>
                                                    <span class="fw-bolder">$${data.subtotal}</span>
                                                </div>
                                                `
                    const strCustomerInfo = `
                                                    <h6>Customer Information</h6>
                                                    <div class="d-flex justify-content-between mb-2">
                                                        <span>Fullname</span>
                                                        <span class="fw-bolder">${data.customer.fullname}</span>
                                                    </div>
                                                    <div class="d-flex justify-content-between mb-2">
                                                        <span>Email</span>
                                                        <span class="fw-bolder">${data.customer.email}</span>
                                                    </div>
                                                    <div class="d-flex justify-content-between mb-2">
                                                        <span>Mobile</span>
                                                        <span class="fw-bolder">${data.customer.mobile}</span>
                                                    </div>
                                                    <div class="d-flex justify-content-between mb-2">
                                                        <span>Address</span>
                                                        <span class="fw-bolder">${data.customer.address}</span>
                                                    </div>
                                                    `
                    pageOrderList.elements.orderInfo.append(strOrderInfo)
                    pageOrderList.elements.customerInfo.append(strCustomerInfo)
                })

            $.ajax({
                url: pageOrderList.url.getOrderDetailByOrderId + orderId,
            })
                .done((data) => {
                    const orderDetailContent =  data.map(
                        (item) => `
                                        <tr>
                                            <td style="width: 250px">
                                                <div class="d-flex align-items-center">
                                                    <img src="${item.product.img}" style="width: 50px">
                                                    ${item.product.title}
                                                </div>
                                            </td>
                                            <td class="text-end align-middle">
                                                ${item.quantity}
                                            </td>
                                            <td class="text-end align-middle">
                                                $${item.product.newPrice}
                                            </td>
                                            <td class="text-end align-middle fw-bolder">
                                                $${item.quantity*item.product.newPrice}
                                            </td>
                                        </tr> 
                                           `
                    )
                    pageOrderList.elements.orderDetailInfo.append(orderDetailContent.join(""))

                })

            pageOrderList.elements.btnClose.on("click", () => {
                trOrder.addClass("col-md-12")
                trOrder.removeClass("col-md-7")
                pageOrderList.elements.orderDetailContent.addClass("hide")
            })
        })
    })
}

$.ajaxSetup({
    headers: {
        "Content-type": "application/json; charset=UTF-8"
    }
})

$(async () => {
    await  pageOrderList.loadData.getAllOrders()
})