<!DOCTYPE html>
<html>
    <head>
        <title>Prova - Veículos</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <h1>Consulta de Veículos</h1>

            <div class="well">
                <p>Informe o modelo OU marca para pesquisar:</p>
                <form class="form-inline">
                    <div class="form-group">
                        <label for="filter-modelo">Modelo</label>
                        <input type="text" class="form-control" id="filter-modelo" name="filter-modelo">
                    </div>
                    <div class="form-group">
                        <label for="filter-marca">Marca</label>
                        <select id="filter-marca" name="filter-marca" class="form-control"></select>
                    </div>
                    <button type="button" class="btn btn-primary btn-filter">Filtrar</button>
                    <a href="/" title="Voltar" class="btn btn-default">Voltar</a>
                </form>
            </div>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th width="50" class="text-center">ID</th>
                    <th width="150">Marca</th>
                    <th>Modelo</th>
                    <th width="100">Categoria</th>
                    <th width="100">Ano</th>
                    <th width="120">Cor</th>
                </tr>
                </thead>
                <tbody class="table-data">

                </tbody>
            </table>
        </div>
        <script>

            var list = function() {
                $.getJSON('/api/veiculos?modelo=' + $('#filter-modelo').val() + '&marca=' + $('#filter-marca').val(), function (data) {
                    var items = [];
                    $.each(data, function (i, veiculo) {
                        items.push(
                            '<tr>' +
                                '<td class="text-center">' + veiculo.id + '</td>' +
                                '<td>' + veiculo.marca.nome + '</td>' +
                                '<td>' + veiculo.modelo + '</td>' +
                                '<td>' + veiculo.categoria + '</td>' +
                                '<td>' + veiculo.ano + '</td>' +
                                '<td>' + veiculo.cor + '</td>' +
                            '</tr>'
                        );
                    });
                    if (items.length > 0) {
                        $('.table-data').html(items.join(''));
                    } else {
                        $('.table-data').html('<tr><td colspan="' + $('.table thead th').length + '" class="text-center">Não há registros para listar</td></tr>');
                    }
                });
            };

            $(document).ready(function () {

                list();

                $('.btn-filter').on('click', function () {
                    list();
                });

                $.getJSON('/api/marcas', function (data) {
                    var options = ['<option value="">[Selecione a marca]</option>'];
                    $.each(data, function (i, marca) {
                        options.push('<option value="' + marca.id + '">' + marca.nome + '</option>');
                    });
                    $('#filter-marca').html(options.join(''));
                });
            });

        </script>
    </body>
</html>
