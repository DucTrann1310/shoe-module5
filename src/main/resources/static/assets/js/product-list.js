const pageProductList = {
    url: {
        getAllProducts: 'http://localhost:8080/api/shoe-ecommerce'
    },
    elements: {},
    commands: {},
    loadData: {}
}

pageProductList.elements.productListContent = $(".table-list tbody")

async function getAllProducts() {
    return await $.ajax({
        url: pageProductList.url.getAllProducts
    })
}

pageProductList.loadData.getAllProducts = async () => {
    const productList = await getAllProducts()

    const productContent = productList.map(
        (product) => `
                            <tr>
                                <td class="align-middle" style="min-width: 250px">
                                    <div class="d-flex align-items-center">
                                        <img src="${product.img}" style="width: 50px">
                                        <span class="ms-2">${product.title}</span>
                                    </div>
                                </td>
                                <td class="align-middle">
                                    <span class="badge px-2 py-1 border ${product.color.name === "White" ? "text-black" : "text-white"}" style="background-color: ${product.color.name}">${product.color.name}</span>
                                </td>
                                <td class="align-middle" >
                                    ${product.category.name}
                                </td>
                                <td class="align-middle" >
                                    ${product.company.name}
                                </td>
                                <td class="align-middle">
                                    <div class="d-flex flex-column">
                                        <del>$${product.prevPrice}</del>
                                        <span>$${product.newPrice}</span>
                                    </div>
                                </td>
                                <td>
                                    <div class="d-flex flex-column align-items-center justify-content-center">
                                        <div class="d-flex align-items-center justify-content-center">
                                            <span class="me-1">${product.star}</span>
                                            <i class="fa-solid fa-star text-warning"></i>
                                        </div>
                                        <div class="d-flex align-items-center justify-content-center">
                                            <span class="me-1">${product.reviews}</span>
                                            <i class="fa-solid fa-eye text-success"></i>
                                        </div>
                                    </div>
                                </td>
                                <td class="align-middle text-center">
                                    <button class="icon-button editProduct" data-id="${product.id}">
                                        <i class="fa-solid fa-pen-to-square text-success"></i>
                                    </button>
                                    <button class="icon-button deleteProduct" data-id="${product.id}">
                                        <i class="fa-solid fa-trash text-danger"></i>
                                    </button>
                                </td>
                            </tr>
                            `
    )
    pageProductList.elements.productListContent.append(productContent.join(""))
}

$.ajaxSetup({
    headers: {
        "Content-type": "application/json; charset=UTF-8"
    }
})

$(async () => {
    await  pageProductList.loadData.getAllProducts()
})