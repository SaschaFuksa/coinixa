import plotly.graph_objects as go
import dash_bootstrap_components as dbc
import dash_core_components as dcc
import dash_html_components as html
import dash
import pandas as pd
from dash.dependencies import Input, Output, State
from dash import Output, Input

df_coins = pd.read_csv(r'C:\Users\Jakob\PycharmProjects\Coinixa\js447\Data\UI\coins.csv')
df_apis = pd.read_csv(r'C:\Users\Jakob\PycharmProjects\Coinixa\js447\Data\UI\apis.csv')

######## Initialize the app#######
app = dash.Dash(__name__,
                suppress_callback_exceptions=True,
                external_stylesheets=[dbc.themes.BOOTSTRAP],
                meta_tags=[
                    {"name": "viewport", "content": "width=device-width, initial-scale=1"}
                ])
app.config.suppress_callback_exceptions = True

###########App Layout############

app.layout = html.Div(
    className='content',
    children=[

        html.Div(
            [
                html.Span([
                    html.A([
                        html.Img(src=app.get_asset_url('large_coinixa.png'), style={'height': '30px'}),
                    ], className='logo-link align-self-center', href='/'),

                    html.Span(dbc.Nav(
                        [
                            # dbc.NavItem(dbc.NavLink("View 1", href="/view1", id='view-link')),
                            # dbc.NavItem(dbc.NavLink("View 2", href="/view2", id='view2-link')),
                        ],
                        pills=True,
                        className='nav-menu',
                        id='navbar',
                    ),
                    )
                ], className="logo-and-nav"),

            ],
            className='header align-self-center justify-content-between'
        ),

        #####Row 1####

        dbc.Row([

            dbc.Col([

                html.Div(
                    html.H1('Use APIs'), className='map-margin'

                ),

                html.Div(
                    dcc.Checklist(

                        df_apis['ApiID'],
                        ['Binance'],

                        # inline=False,
                        id='select-api',
                        inputStyle={"margin-right": "50px", "margin-left": "50px"},
                        className='checklist-items'
                    )

                ),

            ], className='card-tab card', width=True),

            dbc.Col([
                html.H1('Coin'),
                dcc.Dropdown(df_coins['CoinID'], 'Bitcoin', id='coin-dropdown'),
            ], className='card-tab card', width=True),

            dbc.Col([

                html.Div(
                    html.H1('Actual value'),
                    className='map-margin'

                ),
                ####Actual value displayed########

                html.Div([], id='actual-value'),

            ], className='card-tab card', width=True),

        ]),

        #####Row 2####

        dbc.Row([

            dbc.Col([
                html.Div([
                    html.Div([
                        html.Div(
                            html.H1('Exchange rate'), className='map-margin'
                        ),
                        html.Div([
                            dcc.RangeSlider(
                                min=0,
                                max=2,
                                step=None,
                                marks={
                                    0: '72H',
                                    1: '48H',
                                    2: '24H'

                                },
                                value=[0, 2],
                                id='slider'
                            )
                        ]),

                        html.Div(
                            dcc.Graph(id='exchange-rate-graph',
                                      config={'responsive': True},
                                      className='chart'),
                        ),
                    ]),

                ]),
            ], className='card-tab card', width=True),

        ]),

        #####Row 3####

        dbc.Row([

            dbc.Col([
                html.H1('Notification mail'),
                dcc.Input(id="mail-input", type="text", placeholder=" your mail...", style={'width': '200px',
                                                                                            'margin-left': 20,
                                                                                            'display': 'inline-block'

                                                                                            }),
                dcc.Input(id="threshold-input", type="text", placeholder=" value...", style={'width': '200px',
                                                                                             'margin-left': 20,
                                                                                             'display': 'inline-block'

                                                                                             }),

                html.Button('Submit', id='submit-button', n_clicks=0),
            ], className='card-tab card', width=True),

        ]),

    ])


####Callbacks#####

####Callback Exchange-rate-graph#############
@app.callback(
    Output('exchange-rate-graph', 'figure'),
    [Input('select-api', 'value'),
     Input('coin-dropdown', 'value'),
     Input('slider', 'value')])
def update_graph(api_id, coin_id, slider_data):
    print(slider_data)
    print(coin_id)
    print(api_id)
    print(len(api_id))
    print(api_id[0])

#####single api#########

    if len(api_id) == 1:
        df2 = pd.read_csv(
            'C:\\Users\\Jakob\\PycharmProjects\\Coinixa\\js447\Data\\' + api_id[0] + '\\'
            + coin_id + '\\candleStickData_' + api_id[0] + '_' + coin_id + '.csv',
            parse_dates=True)

        df2.columns = df2.columns.str.strip()
        print(len(df2.index))
        return {'data': [{
            'x': df2['closeTimeStamp'],
            'open': df2['open'],
            'high': df2['high'],
            'low': df2['low'],
            'close': df2['close'],
            'type': 'candlestick',
        }],
            'layout': {
                'margin': {'b': 0, 'r': 10, 'l': 10, 't': 0},
                'legend': {'x': 0},
                # 'xaxis_rangeslider_visible': 'False',
                'xaxis': {'range': [slider_data[0] * len(df2.index) / 3, slider_data[1] * len(df2.index) / 2]
                          }
            }
        }

    ######multi api########

    if len(api_id) > 1:

        df2 = pd.read_csv(
            'C:\\Users\\Jakob\\PycharmProjects\\Coinixa\\js447\Data\\' + api_id[0] + '\\'
            + coin_id + '\\candleStickData_' + api_id[0] + '_' + coin_id + '.csv',
            parse_dates=True)

        df2.columns = df2.columns.str.strip()

        ##Compare Apis###

        # Create graph
        fig = go.Figure()

        # add different datasets to graph
        a = 0
        for i in api_id:
            df2 = pd.read_csv(
                'C:\\Users\\Jakob\\PycharmProjects\\Coinixa\\js447\Data\\' + api_id[0] + '\\'
                + coin_id + '\\candleStickData_' + api_id[0] + '_' + coin_id + '.csv',
                parse_dates=True)
            df2.columns = df2.columns.str.strip()
            fig.add_trace(go.Scatter(x=df2['closeTimeStamp'], y=df2['high']))
            a = a + 1
        fig.update_layout(xaxis_rangeslider_visible=False)
        return fig

    else:
        df2 = pd.read_csv(
            'C:\\Users\\Jakob\\PycharmProjects\\Coinixa\\js447\Data\\Binance\\'
            + coin_id + '\\candleStickData_Binance_' + coin_id + '.csv',
            parse_dates=True)
        df2.columns = df2.columns.str.strip()
        fig1 = go.Figure()
        fig1.add_trace(go.Scatter(x=df2['closeTimeStamp'], y=df2['high']))
        return fig1


########Calllback actual value tab###########

@app.callback(
    Output('actual-value', 'children'),
    [Input('select-api', 'value'),
     Input('coin-dropdown', 'value')])
# function setactualvalue builds the actual value tab and coin specific information

def setactualvalue(api_id, coin_id):
    df2 = pd.read_csv(
        'C:\\Users\\Jakob\\PycharmProjects\\Coinixa\\js447\Data\\' + api_id[0] + '\\'
        + coin_id + '\\candleStickData_' + api_id[0] + '_' + coin_id + '.csv',
        parse_dates=True)
    df2.columns = df2.columns.str.strip()
    return [
        html.Div(

            # Zuweisung actual value, 4th row, last entry
            html.H4(str(df2.iloc[-1, 3]) + " $")
        )
    ]


#######Callback email notification#############


@app.callback(
    Output("newsletter", "children"),
    [Input("submit", "n_clicks")],
    [State("mail-input", "value"), State("threshold-input", "value")])
def adduser(a, b, c):
    print(a)
    return None

    # fig.update_yaxes(range=[minY, maxY])
    # fig.update_layout(xaxis_rangeslider_visible=False)


# server
if __name__ == '__main__':
    app.run_server(debug=True)
