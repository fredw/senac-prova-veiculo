<!DOCTYPE html>
<html>
    <head>
        <title>Prova - Veículos</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container">
            <h1>Cadastro de Marca</h1>
            <form method="post">
                <div class="alert alert-success hide"></div>
                <div class="alert alert-danger hide"></div>
                <div class="form-group">
                    <label for="nome">Nome</label>
                    <input type="text" id="nome" name="nome" class="form-control" maxlength="50" required>
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
                        url: '/api/marcas',
                        data: JSON.stringify({
                            nome: $('#nome').val()
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
            });

        </script>
    </body>
</html>
