In this project, there are two apps: **Storage** and **Search**. **Storage** fetches remote data about device/products and saves that data in Room DB locally. 
Then, user opens **Search** and inputs a query (any literal). Once, user clicks search, **Storage** app is opened and look for query in fetched products list. In any field of any product.
And shows result product list. If user clicks item from result list, **Storage** makes a request to backend for details of item, and user sees item detail page. Intent transferring organized via signature permission mechanism. So that, app imposter can't intercept intent. 

Release and debug keystores are added to repo intentionally, to test apps' interaction easily. As soon as apps are signed with same key (debug or release), intent passed between them couldn't be intercepted by any other app. In real situation keystores and password can be system envs, or Github Actions secrets.
