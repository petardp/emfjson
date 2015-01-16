window.onload = function() {

    var menuEl = document.getElementById('menu');
    var h = document.querySelectorAll('h1, h2, h3');

    var MenuItem = React.createClass({displayName: "MenuItem",

        render: function() {
            return (
                React.createElement("div", null, 
                    React.createElement("div", null, 
                        React.createElement("i", null)
                    ), 
                    React.createElement("div", {className: this.props.className}, 
                        React.createElement("a", {href: this.props.id}, this.props.label)
                    )
                )
            )
        }

    })

    var Menu = React.createClass({displayName: "Menu",

        render: function() {
            console.log(this.props);
            return (
                React.createElement("div", null, 
                
                    this.props.items.map(function(item) {
                        return React.createElement(MenuItem, {id: item.id, label: item.innerHTML});
                    })
                
                )
            )
        }

    })

    function menuClass(el) {
        var nodeName = el.nodeName;

        if (nodeName === 'H1') return 'menu-h1';
        if (nodeName === 'H2') return 'menu-h2';
        return 'menu-h3';
    }

    console.log(h, menuEl);
    React.render(React.createElement(Menu, {items: _.toArray(h)}), menuEl);
}
