using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class TelefoneUsuario
    {
        public int CdTelefone { get; set; }
        public int CdUsuario { get; set; }
        public string NrTelefone { get; set; }
        public string NrDdd { get; set; }
        public string DsTelefone { get; set; }
        public DateTime DtCadastro { get; set; }
        public DateTime? DtAtualizacao { get; set; }

        [JsonIgnore]
        public Usuario CdUsuarioNavigation { get; set; }
    }
}
