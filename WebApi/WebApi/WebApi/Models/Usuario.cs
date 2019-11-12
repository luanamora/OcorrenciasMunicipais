using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class Usuario
    {
        public Usuario()
        {
            HistoricoSenha = new HashSet<HistoricoSenha>();
            ImagemUsuario = new HashSet<ImagemUsuario>();
            Ocorrencia = new HashSet<Ocorrencia>();
            TelefoneUsuario = new HashSet<TelefoneUsuario>();
            UsuarioAreaatendimento = new HashSet<UsuarioAreaatendimento>();
        }


        [JsonProperty("cd_usuario")]
        public int CdUsuario { get; set; }
        [JsonProperty("nm_usuario")]
        public string NmUsuario { get; set; }
        [JsonProperty("ds_senha")]
        public string DsSenha { get; set; }
        [JsonProperty("ds_email")]
        public string DsEmail { get; set; }
        [JsonProperty("dt_nascimento")]
        public DateTime DtNascimento { get; set; }
        [JsonProperty("st_status")]
        public bool StStatus { get; set; }
        [JsonProperty("st_administrador")]
        public bool StAdministrador { get; set; }
        [JsonProperty("dt_cadastro")]
        public DateTime DtCadastro { get; set; }
        [JsonProperty("dt_atualizacao")]
        public DateTime? DtAtualizacao { get; set; }


        [JsonIgnore]
        public ICollection<HistoricoSenha> HistoricoSenha { get; set; }
        [JsonIgnore]
        public ICollection<ImagemUsuario> ImagemUsuario { get; set; }
        [JsonIgnore]
        public ICollection<Ocorrencia> Ocorrencia { get; set; }
        [JsonIgnore]
        public ICollection<TelefoneUsuario> TelefoneUsuario { get; set; }
        [JsonIgnore]
        public ICollection<UsuarioAreaatendimento> UsuarioAreaatendimento { get; set; }
    }
}
