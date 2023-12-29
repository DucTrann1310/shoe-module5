const pageShoe = {
    url:{
        getAllCategories: 'http://localhost:8080/api/shoe-ecommerce/category',
        getAllColors: 'http://localhost:8080/api/shoe-ecommerce/color',
        getAllCompanies: 'http://localhost:8080/api/shoe-ecommerce/company',
        getAllProducts: 'http://localhost:8080/api/shoe-ecommerce',
        getSizeCartDetail: 'http://localhost:8080/api/shoe-ecommerce/cartDetail',
        getAllPrices: 'http://localhost:8080/api/shoe-ecommerce/price',
        filterProducts: 'http://localhost:8080/api/shoe-ecommerce/filter',
        addToCart: 'http://localhost:8080/api/shoe-ecommerce/addToCart'
    },
    elements: {},
    commands: {},
    loadData:{}
}


let pageCurrent = 0
pageShoe.elements.categoryContent = $("#categoryContent")
pageShoe.elements.colorContent = $("#colorContent")
pageShoe.elements.companyContent = $("#companyContent")
// pageShoe.elements.btnCompany = $(".btn-sm")
pageShoe.elements.productContent = $("#productContent")
pageShoe.elements.priceContent = $("#priceContent")

pageShoe.elements.cartUser = $("#cart-user")



let buttonValue = $(".btn-sm").filter(function() {
    return $(this).hasClass("active");
  }).text();



async function getAllCategories(){
    return await $.ajax({
        url: pageShoe.url.getAllCategories
    })
}

async function getAllColors(){
    return await $.ajax({
        url: pageShoe.url.getAllColors
    })
}

async function getAllCompanies(){
    return await $.ajax({
        url: pageShoe.url.getAllCompanies
    })
}

async function getAllProducts(){
    return await $.ajax({
        url: pageShoe.url.getAllProducts
    })
}

async function getAllPrices() {
    return await $.ajax({
        url: pageShoe.url.getAllPrices
    })
}

async function getAllCartDetails() {
    return await  $.ajax({
        url: pageShoe.url.getSizeCartDetail
    })
}

pageShoe.loadData.getAllCategories = async() => {
    const categories = await getAllCategories();

    categories.forEach(item => {
        const str = pageShoe.commands.renderCategory(item)

        pageShoe.elements.categoryContent.append(str)
    })

}

pageShoe.loadData.getAllColors = async() => {
    const colors = await getAllColors();
    colors.forEach(item => {

        const str = pageShoe.commands.renderColor(item)

        pageShoe.elements.colorContent.append(str)
    })
}

pageShoe.loadData.getAllCompanies = async() => {
    const companies = await getAllCompanies();

    companies.forEach(item => {
        const str = pageShoe.commands.renderCompany(item)

        pageShoe.elements.companyContent.append(str)
    })

    pageShoe.commands.companyButtonActive()
}

pageShoe.loadData.getAllProducts = async() => {
    const products = await getAllProducts();

    products.forEach(item => {
        const str = pageShoe.commands.renderProduct(item)
        pageShoe.elements.productContent.append(str)
    })
}

pageShoe.loadData.getAllPrices = async () => {
    const prices = await getAllPrices()
    prices.forEach(item => {
        const str = pageShoe.commands.renderPrice(item)
        pageShoe.elements.priceContent.append(str)
    })
}

pageShoe.loadData.getCartUser = async () => {
    const cartDetails = await getAllCartDetails()

    pageShoe.elements.cartUser.empty()


    if (cartDetails.length == 0) {
        const str = `
                    <i class="fa-solid fa-cart-shopping fa-lg" style="padding-right: 10px; margin-top: 13px"></i>
                    <a class="icon-button user-icon" href="/shoe-ecommerce/dashboard/order-list">
                        <i class="fa-solid fa-user fa-lg custom-icon"></i>
                    </a>
                            `

        pageShoe.elements.cartUser.append(str)
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

        pageShoe.elements.cartUser.append(str)
    }
}

pageShoe.loadData.getContent = async() => {
    await pageShoe.loadData.getAllCategories()
    await pageShoe.loadData.getAllColors()
    await pageShoe.loadData.getAllCompanies()
    await pageShoe.loadData.getAllPrices()
    await pageShoe.loadData.getCartUser()
    // await pageShoe.loadData.getAllProducts()
}

pageShoe.commands.renderCategory = (obj) => {
    return `
            <div class="form-check py-1">
                <input class="form-check-input" type="radio" name="category" id="cat_${obj.id}" value="${obj.name}">
                <label for="cat_${obj.id}" role="button" class="form-check-label ">${obj.name}</label>
            </div>
        `;
}

pageShoe.commands.renderColor = (obj) => {
    return `
            <div class="form-check py-1">
                <input class="form-check-input" type="radio" name="color" id="color_${obj.id}" value="${obj.name}"
                    style="background-color: ${obj};">
                <label role="button" for="color_${obj.id}" class="form-check-label ">${obj.name}</label>
             </div>
            `
}

pageShoe.commands.renderCompany = (obj) => {
    return `
            <button class="btn btn-sm btn-outline-secondary me-1" type="button">${obj.name}</button>
            `
}

pageShoe.commands.renderProduct = (obj) => {
    let str ='';
    for(let i = 0; i< obj.star; i ++){
        str += `                                        
                <svg stroke="currentColor" fill="currentColor" stroke-width="0"viewBox="0 0 576 512" color="yellow" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg" style="color: yellow;">
                <path d="M259.3 17.8L194 150.2 47.9 171.5c-26.2 3.8-36.7 36.1-17.7 54.6l105.7 103-25 145.5c-4.5 26.3 23.2 46 46.4 33.7L288 439.6l130.7 68.7c23.2 12.2 50.9-7.4 46.4-33.7l-25-145.5 105.7-103c19-18.5 8.5-50.8-17.7-54.6L382 150.2 316.7 17.8c-11.7-23.6-45.6-23.9-57.4 0z">
                </path>
                </svg>
            `
    }
    return `
            <div class="col-md-3 mb-4">
                <div class="card d-flex align-items-center pt-2" style="height: 345px">
                    <div class="d-flex align-items-center justify-content-center" style="height: 60%">
                        <img src="${obj.img}" class="card-image-top" alt style="width: 70%;">
                    </div>
                    <div class="card-body">
                        <p class="fw-bolder">${obj.title}</p>
                        <div class="d-flex align-items-center mb-2">
                            <div class="me-1">
                                ${str}
                            </div>
                            <div class="fs-10">
                                (${obj.reviews} reviewer)
                            </div>
                        </div>
                        <div class="d-flex align-items-center justify-content-between">
                            <div>
                                <del class="line-through me-2">
                                   $${obj.prevPrice}
                                </del>
                                <span>$${obj.newPrice}</span>
                            </div>
                            <button class="icon-button addToCart" data-id="${obj.id}" data-title="${obj.title}">
                                <i class="fa-solid fa-cart-arrow-down fa-lg"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            `
}

pageShoe.commands.renderPrice = (obj) => {
    return  `                    
                <div class="form-check py-1">
                    <input class="form-check-input" type="radio" name="price" id="price_${obj.id}" value="${obj.minPrice},${obj.maxPrice}" ${obj.maxPrice == 0 ? "checked" : ""}>
                    <label role="button" for="price_${obj.id}" class="form-check-label ${obj.maxPrice == 0 ? "text-decoration-underline fw-bolder" : ""}">
                              ${obj.minPrice == obj.maxPrice ? (obj.minPrice == 0 ? 'All' : ('Over $' + obj.minPrice)) : ('$' + obj.minPrice + ' - $' + obj.maxPrice)}
                    </label>
                </div>
            `

}

pageShoe.commands.companyButtonActive = () => {
    pageShoe.elements.companyButtonActive = $(".btn-sm")


    pageShoe.elements.companyButtonActive.on("click", function (){
        pageShoe.elements.companyButtonActive.removeClass("active")

        $(this).addClass("active")

        buttonValue = $(this).text();

        pageShoe.commands.searchByOptions()
    })
}

pageShoe.commands.searchByOptions = async(pageCurrent) => {
    const inputSearch = $("#inputSearch").val().toLowerCase()
    const priceSearch = $('#priceContent input[type="radio"]:checked').val()
    const minPrice = priceSearch.split(",")[0]
    const maxPrice = priceSearch.split(",")[1]
    const companySearch = buttonValue.toLowerCase()
    const colorSearch = $('#colorContent input[type="radio"]:checked').val().toLowerCase()
    const categorySearch = $('#categoryContent input[type="radio"]:checked').val().toLowerCase()

    function fetchData(url){
        const res = fetch(url)
    }

    const filter = {
        input: inputSearch,
        minPrice: minPrice,
        maxPrice: maxPrice,
        company: companySearch,
        color: colorSearch,
        category: categorySearch
    }
    $.ajax({
        url: pageShoe.url.filterProducts,
        data: {
            ...filter,
            page: pageCurrent,
            size: 5
        },
    })
        .done((data) => {
            pageShoe.elements.productContent.empty()

            data.content.forEach(item => {
                const str = pageShoe.commands.renderProduct(item);
                pageShoe.elements.productContent.append(str);
            });

            pageShoe.commands.addToCart()
            pageShoe.commands.renderPage(data.totalPages, pageCurrent)

        })
    console.log("input: " + inputSearch);
    console.log("price: " + priceSearch);
    console.log("company: " + companySearch);
    console.log("color: " + colorSearch);
    console.log("category: " + categorySearch);
    console.log("---------------");
}
pageShoe.commands.changeOptionSearch = () => {

    $.each(pageShoe.elements.categorySearch, (index, item)=> {
        $(item).click(function() {
          pageShoe.commands.searchByOptions(pageCurrent);
        });
      });
      
      $.each(pageShoe.elements.priceSearch, (index, item)=> {
        $(item).click(function() {
          pageShoe.commands.searchByOptions(pageCurrent);
        });
      });
 

    $.each(pageShoe.elements.colorSearch, (index, item)=> {
        $(item).click(function() {
          pageShoe.commands.searchByOptions(pageCurrent);
        });
      });

    pageShoe.elements.inputSearch.on("input", () => {
        pageShoe.commands.searchByOptions(pageCurrent)
    })
}
pageShoe.commands.renderPage = (totalPages, pageCurrent) => {
    pageShoe.elements.pageContent = $(".pagination")
    if(totalPages === 0 || totalPages === 1){
        pageShoe.elements.pageContent.empty()
        return
    }
    pageShoe.elements.pageContent.empty()
    const strPrevious = `
                                 <li class="page-item page-cursor page-previous  ${pageCurrent === 0 ? "disabled" : ""}"><span class="page-link">Previous</span></li>
                                `

    const strNext = `
                             <li class="page-item page-cursor page-next ${pageCurrent === totalPages - 1 ? "disabled" : ""}"><span class="page-link">Next</span></li>
                            `
    pageShoe.elements.pageContent.append(strPrevious)
    for(let i = 0; i<totalPages; i++){
        const strPageNumber = `
                                <li class="page-item page-cursor page-number ${i === pageCurrent ? "active" : ""}" data-page="{i}" ><span class="page-link">${i + 1}</span></li>
                             `
        pageShoe.elements.pageContent.append(strPageNumber)
    }
    pageShoe.elements.pageContent.append(strNext)

    pageShoe.elements.pageNumber = $(".page-number")
    pageShoe.elements.pagePrevious = $(".page-previous")
    pageShoe.elements.pageNext = $(".page-next")

    $.each(pageShoe.elements.pageNumber, (index, item) => {
        $(item).on("click", function (){
            pageCurrent = parseInt($(this).children("span").text()) - 1;
            pageShoe.commands.searchByOptions(pageCurrent)
        })
    })
    if(pageCurrent > 0 ){
        pageShoe.elements.pagePrevious.on("click", ()=>{
            pageCurrent = pageCurrent - 1
            pageShoe.commands.searchByOptions(pageCurrent)
        })
    }

    if(pageCurrent < totalPages - 1){
        pageShoe.elements.pageNext.on("click", () => {
            pageCurrent = pageCurrent + 1
            console.log(totalPages)
            pageShoe.commands.searchByOptions(pageCurrent)
        })
    }


}

pageShoe.commands.addToCart = () => {
    pageShoe.elements.addToCart = $(".addToCart")

    $.each(pageShoe.elements.addToCart, (index, item) => {

        $(item).on("click", function (){
            const productId = $(this).data("id")
            const productName = $(this).data("title")
            const addToCart = {
                productId
            }
            $.ajax({
                method: 'POST',
                url: pageShoe.url.addToCart,
                data: JSON.stringify(addToCart)
            })
                .done(() => {
                    pageShoe.loadData.getCartUser()
                    AppUtils.showSuccess(productName + " added to cart", "success")
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
    await pageShoe.loadData.getContent()

    await pageShoe.commands.searchByOptions(pageCurrent)

    pageShoe.elements.categorySearch = $('#categoryContent input[type="radio"]')
    pageShoe.elements.priceSearch = $('#priceContent input[type="radio"]')
    pageShoe.elements.colorSearch = $('#colorContent input[type="radio"]')
    pageShoe.elements.inputSearch = $("#inputSearch")
    pageShoe.commands.changeOptionSearch()
    pageShoe.commands.addToCart()
})
