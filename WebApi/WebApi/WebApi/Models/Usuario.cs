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

        public int CdUsuario { get; set; }
        public string NmUsuario { get; set; }
        public string DsSenha { get; set; }
        public string DsEmail { get; set; }
        public DateTime DtNascimento { get; set; }
        public bool StStatus { get; set; }
        public bool StAdministrador { get; set; }
        public DateTime DtCadastro { get; set; }
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
