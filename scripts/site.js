window.onload = function() {

    var menuEl = document.getElementById('menu');
    var h = document.querySelectorAll('h1, h2, h3');

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
            var style = this.state.collapsed ? "" : "";

            return (
                <div>
                    <div onClick={this.handleClick}>
                        <i className="icon-plus-squared"/>
                    </div>
                    <div className={this.props.className}>
                        <a href={this.props.id}>{this.props.label}</a>
                    </div>
                </div>
            )
        }

    })

    var Menu = React.createClass({

        render: function() {
            console.log(this.props);
            return (
                <div>
                {
                    this.props.items.map(function(item) {
                        return <MenuItem id={item.id} label={item.innerHTML} />;
                    })
                }
                </div>
            )
        }

    })

    function typeOf(element) {
        return element.nodeName;
    }

    {
        label: ee,
        id: id,
        leafs: [
            {

            }
        ]
    }

    function asTree(elements) {
        var tree = {};

        var current;
        _.map(elements, function(element) {
            current = element;
            if (typeOf(element) != 'H1') {

            }
        })
    }

    console.log(h, menuEl);
    React.render(<Menu items={_.toArray(h)} />, menuEl);
}
