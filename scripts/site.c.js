var MenuItem = React.createClass({displayName: "MenuItem",

    getInitialState: function() {
        return {
            collapsed: true
        }
    },

    handleClick: function() {
        this.setState({
            collapsed: !this.state.collapsed
        })
    },

    render: function() {
        var item = this.props.item;
        var style = item.children.length ?
            (this.state.collapsed ?
                "icon-right-dir" :
                "icon-down-dir") : 'empty';

        var className = 'menu-h' + item.type;
        var children = this.state.collapsed ? [] : item.children;

        return (
            React.createElement("div", {className: "menu-node"}, 
                React.createElement("div", {onClick: this.handleClick}, 
                    React.createElement("i", {className: style})
                ), 
                React.createElement("div", {className: className}, 
                    React.createElement("a", {href: '#' + item.id}, item.label)
                ), 
                
                    children.map(function(i) {
                        return React.createElement(MenuItem, {key: i.id, item: i})
                    })
                
            )
        )
    }
})

var Menu = React.createClass({displayName: "Menu",

    render: function() {
        return (
            React.createElement("div", null, 
            
                this.props.items.map(function(item) {
                    return React.createElement(MenuItem, {key: item.id, item: item})
                })
            
            )
        )
    }
})

function parent(current, previous) {
    if (!previous) return null;

    if (previous.type < current.type)
        return previous;
    else if (previous.type === current.type)
        return previous.parent;
    else return previous.parent.parent;
}

function createItems(nodes) {
    var node, current, previous, result = [];

    for (var i = 0; i < nodes.length; i++) {
        node = nodes[i];

        current = {
            type: parseInt(node.nodeName.slice(1), 10),
            id: node.id,
            label: node.innerHTML,
            children: []
        }
        current.parent = parent(current, previous);

        if (current.parent) {
            current.parent.children.push(current);
        } else {
            result.push(current);
        }

        previous = current;
    }

    return result;
}

window.onload = function() {
    var menuEl = document.getElementById('menu');
    var nodes = _.toArray(document.querySelectorAll('h1, h2, h3'));

    React.render(React.createElement(Menu, {items: createItems(nodes)}), menuEl);
}
