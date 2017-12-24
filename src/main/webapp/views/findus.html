<md-content>
    <link rel="stylesheet" href="css/rating.css">

    <div class="findus">

        <style>
            .findus {
                background: url(js/Lib/slider/images/bg7.jpg) no-repeat center center fixed;
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }


        </style>

        <md-card  md-theme="{{ showDarkTheme ? 'dark-blue' : 'default' }}" md-theme-watch style="background-image:url(js/Lib/slider/images/bg21.jpg) "  >

            <md-card-title>
                <md-card-title-text>
                    <span class="md-headline">{{restaurant.Restaurant_Name}}</span>
                    <span class="md-subhead">{{restaurant.Location}}</span>
                </md-card-title-text>
                <md-card-title-media>
                    <div class="md-media-lg card-media">

                        <img ng-src="{{restaurant.RImage}}" class="md-card-image" alt="Washed Out" style="width:100%;height:100%"/>
                    </div>
                </md-card-title-media>
            </md-card-title>
            <md-card-content>
                <p>Email: {{restaurant.Email}}</p>
                <p>Contact us: {{restaurant.RContact}}</p>
                </p>Ordering Facility: {{restaurant.OFacility}}</p>
            </md-card-content>
            <md-card-actions layout="row" layout-align="end center">
                <div layout="row" ng-cloak md-theme="{{theme}}" class="container">

                    <md-button  class="md-primary md-raised" ng-click="showAdvanced(ev ,restaurant)" style="background-color: darkgreen">ADD A COMMENT</md-button>
                </div>
                <!--<md-button style="background-color: darkgreen">RATING</md-button>-->



                <md-card md-theme="{{ showDarkTheme ? 'dark-purple' : 'default' }}" md-theme-watch>
                    <div class="acidjs-rating-stars">
                        <form class="ng-pristine ng-valid" style="width:121px">
                            <input ng-model="rating" type="radio" name="group-1" id="group-1-0" value="50" /><label for="group-1-0"></label>
                            <input ng-model="rating" type="radio" name="group-1" id="group-1-1" value="40" /><label for="group-1-1"></label>
                            <input ng-model="rating" type="radio" name="group-1" id="group-1-2" value="30" /><label for="group-1-2"></label>
                            <input ng-model="rating" type="radio" name="group-1" id="group-1-3" value="20" /><label for="group-1-3"></label>
                            <input ng-model="rating" type="radio" name="group-1" id="group-1-4" value="10" /><label for="group-1-4"></label>
                        </form>
                    </div>
                </md-card>



            </md-card-actions>
        </md-card>

        <div layout="row" >
            <h2>  Distance: {{map.directionsRenderers[0].directions.routes[0].legs[0].distance}} </h2>
        </div>

        <md-divider md-inset ></md-divider>
        <div layout="row" layout-wrap  ng-show="loggedIn">
            <div ng-repeat="item in restaurant.AvailableFoodItems" flex ="25">
                <md-card md-theme="{{ showDarkTheme ? 'dark-blue' : 'default' }}" md-theme-watch style="background-image:url(js/Lib/slider/images/bg23.jpg) ">

                    <md-card-title>
                        <md-card-title-text>
                            <span class="md-headline" style="color:#510BE7">{{item.Name}}</span>
                            <span class="md-subhead" style="color: #3e8f3e"> Avalable Quantity : {{item.Quantity}}</span>
                        </md-card-title-text>
                        <!--<md-card-title-media>-->
                            <!--<div class="md-media-lg card-media">-->

                                <!--<img ng-src="{{}}" class="md-card-image" alt="Washed Out" style="width:100%;height:100%"/>-->
                            <!--</div>-->
                        <!--</md-card-title-media>-->
                    </md-card-title>
                    <md-card-content>
                        <h2 >Unit Price: {{item.Unit_Price}}</h2>
                    </md-card-content>
                    <md-card-actions layout="row" layout-align="end center">
                        <md-input-container class="md-block">
                            <label >Quantity</label>
                            <input required type="number" name="rate" ng-model="quantity"  />
                        </md-input-container>
                        <div class="btn-group" ng-init="style={'background-color':'#204d74'}">
                        <md-button ng-click="addToCart(item , quantity);style1=style" style="background-color: #2299dd" class="btn btn-default" ng-style="style1">Add To Cart</md-button>
                        </div>
                    </md-card-actions>


                </md-card>
            </div>
        </div>

        <md-list-item class="md-3-line" ng-repeat="allItem in allItems" ng-click="null">
            <img ng-src="{{item.face}}?{{$index}}" class="md-avatar" alt="{{item.who}}" />
            <div class="md-list-item-text" layout="column">

                <label>YUMMY CART HELPER</label>
                <h3>{{ allItem.Name }}</h3>
                <h4>{{ allItem.selectedQuantity }}</h4>
            </div>
        </md-list-item>

        <div layout="row" ng-hide="loggedIn">
            <h2>Please Login to See items</h2>
        </div>

        <md-divider md-inset ></md-divider>




        <!--<div flex>-->
        <!--<md-input-container flex>-->
        <!--<label>Enter Current Location</label>-->
        <!--<input type="text" g-places-autocomplete ng-model="currentLocation"/>-->
        <!--</md-input-container>-->
        <!--</div>-->

        <!--<div flex>-->
        <!--<md-button ng-click="showPath()" class="md-raised md-primary">Show Path</md-button>-->
        <!--</div>-->



        <md-card  md-theme="{{ showDarkTheme ? 'dark-blue' : 'default' }}" md-theme-watch style="background-image:url(js/Lib/slider/images/bg8.jpg) ">

            <div flex>
                <md-input-container flex>
                    <label style="color:#ffffff">Enter Current Location</label>
                    <input style="color:#ffffff"   type="text" g-places-autocomplete ng-model="currentLocation"/>
                </md-input-container>
            </div>
            <div flex>
                <md-button ng-click="showPath(map)" class="md-raised md-primary">Show Path</md-button>
            </div>
            <div style="width: 100%;  height: 100%">

                <ng-map zoom="7" center="{{filters.destination}}" style="height:90%">
                    <directions
                            draggable="true"
                            panel="directions-panel"
                            travel-mode="DRIVING"
                            origin="{{filters.currentLocation}}"
                            destination="{{filters.destination}}">
                    </directions>
                </ng-map>
                Directions path length:
                {{map.directionsRenderers[0].directions.routes[0].overview_path.length}}
            </div>
            <div id="directions-panel" style="width: 28%; float:left; height: 100%; overflow: auto; padding: 0px 5px">
            </div>
        </md-card>



    </div>
</md-content>


