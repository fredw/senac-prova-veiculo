<!DOCTYPE html>
<html>
    <head>
        <title>Prova - Veículos</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <h1>Cadastro de Veículo</h1>
            <form method="post">
                <div class="alert alert-success hide"></div>
                <div class="alert alert-danger hide"></div>
                <div class="form-group">
                    <label for="marca">Marca</label>
                    <select id="marca" name="marca" class="form-control" required></select>
                </div>
                <div class="form-group">
                    <label for="modelo">Modelo</label>
                    <input type="text" id="modelo" name="modelo" class="form-control" maxlength="50" required>
                </div>
                <div class="form-group">
                    <label for="categoria">Categoria</label>
                    <select id="categoria" name="categoria" class="form-control" required>
                        <option value="">[Selecione a categoria]</option>
                        <option value="Hatch">Hatch</option>
                        <option value="Sedan">Sedan</option>
                        <option value="SW">SW</option>
                        <option value="Sport">Sport</option>
                        <option value="Pick-up">Pick-up</option>
                        <option value="SUV">SUV</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="ano">Ano</label>
                    <input type="number" id="ano" name="ano" class="form-control" min="1800" max="2050" value="2016" step="1">
                </div>
                <div class="form-group">
                    <label for="cor">Cor</label>
                    <input type="text" id="cor" name="cor" class="form-control" maxlength="50">
                </div>
                <button type="button" name="save" class="btn btn-success btn-save">Salvar</button>
                <a href="/" class="btn btn-default" title="Voltar">Voltar</a>
            </form>
        </div>
        <script>

            $(document).ready(function () {
                $('.btn-save').on('click', function () {
                    $.ajax({
                        type: 'post',
                        contentType: 'application/json',
                        dataType: 'json',
                        url: '/api/veiculos',
                        data: JSON.stringify({
                            marca: {
                                id: $('#marca').val()
                            },
                            modelo: $('#modelo').val(),
                            categoria: $('#categoria').val(),
                            ano: $('#ano').val(),
                            cor: $('#cor').val()
                        }),
                        success: function (data) {
                            $('.alert').addClass('hide');
                            if (data.status == 'success') {
                                $('.alert-success').removeClass('hide').html('Registro salvo com sucesso!');
                                $('input, select').val(null);
                            } else if (data.status == 'error') {
                                $('.alert-danger').removeClass('hide').html('Problema ao salvar registro!');
                            }
                        }
                    });
                });

                $.getJSON('/api/marcas', function (data) {
                    var options = ['<option value="">[Selecione a marca]</option>'];
                    $.each(data, function (i, marca) {
                        options.push('<option value="' + marca.id + '">' + marca.nome + '</option>');
                    });
                    $('#marca').html(options.join(''));
                });
            });

        </script>
    </body>
</html>
