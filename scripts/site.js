var MenuItem = React.createClass({

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
            <div className="menu-node">
                <div onClick={this.handleClick}>
                    <i className={style} />
                </div>
                <div className={className}>
                    <a href={'#' + item.id}>{item.label}</a>
                </div>
                {
                    children.map(function(i) {
                        return <MenuItem key={i.id} item={i} />
                    })
                }
            </div>
        )
    }
})

var Menu = React.createClass({

    render: function() {
        return (
            <div>
            {
                this.props.items.map(function(item) {
                    return <MenuItem key={item.id} item={item} />
                })
            }
            </div>
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

    React.render(<Menu items={createItems(nodes)} />, menuEl);
}
